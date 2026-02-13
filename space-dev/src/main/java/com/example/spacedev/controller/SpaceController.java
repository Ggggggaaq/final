package com.example.spacedev.controller;

import com.example.spacedev.domain.SpaceVenue;
import com.example.spacedev.service.SpaceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class SpaceController {
    private static final String SESSION_USER_KEY = "loginUser";
    private static final String SESSION_ROLE_KEY = "loginRole";

    private static final Map<String, String> DEMO_USERS = Map.of(
            "admin", "space1234",
            "manager", "welcome123",
            "user1", "user1234",
            "guest", "guest1234"
    );

    private static final Map<String, String> USER_ROLES = Map.of(
            "admin", "ADMIN",
            "manager", "ADMIN",
            "user1", "USER",
            "guest", "USER"
    );

    private final SpaceService spaceService;

    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @GetMapping("/")
    public String root(HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        return isAdmin(session) ? "redirect:/admin/dashboard" : "redirect:/user/home";
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        if (isLoggedIn(session)) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        String savedPassword = DEMO_USERS.get(username);
        if (savedPassword == null || !savedPassword.equals(password)) {
            redirectAttributes.addFlashAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "redirect:/login";
        }

        session.setAttribute(SESSION_USER_KEY, username);
        session.setAttribute(SESSION_ROLE_KEY, USER_ROLES.getOrDefault(username, "USER"));
        redirectAttributes.addFlashAttribute("message", "로그인되었습니다.");
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message", "로그아웃되었습니다.");
        return "redirect:/login";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model, HttpSession session) {
        if (!requireAdmin(session)) {
            return "redirect:/login";
        }

        model.addAttribute("venues", spaceService.venues());
        model.addAttribute("reservations", spaceService.recentReservations());
        model.addAttribute("venueCount", spaceService.venueCount());
        model.addAttribute("reservationCount", spaceService.reservationCount());
        model.addAttribute("avgHourlyPrice", spaceService.averageHourlyPrice());
        model.addAttribute("loginUser", session.getAttribute(SESSION_USER_KEY));
        model.addAttribute("loginRole", session.getAttribute(SESSION_ROLE_KEY));
        return "admin-dashboard";
    }

    @GetMapping("/admin/venues")
    public String adminVenues(Model model, HttpSession session) {
        if (!requireAdmin(session)) {
            return "redirect:/login";
        }

        model.addAttribute("venues", spaceService.venues());
        model.addAttribute("topVenue", spaceService.highestCapacityVenue().map(SpaceVenue::name).orElse("-"));
        model.addAttribute("loginUser", session.getAttribute(SESSION_USER_KEY));
        model.addAttribute("loginRole", session.getAttribute(SESSION_ROLE_KEY));
        return "admin-venues";
    }

    @PostMapping("/admin/venues")
    public String addVenue(@RequestParam String name,
                           @RequestParam String location,
                           @RequestParam String region,
                           @RequestParam String imageUrl,
                           @RequestParam int capacity,
                           @RequestParam int hourlyPrice,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        if (!requireAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "관리자만 접근 가능합니다.");
            return "redirect:/login";
        }

        spaceService.createVenue(name, location, region, imageUrl, capacity, hourlyPrice);
        redirectAttributes.addFlashAttribute("message", "공간이 등록되었습니다.");
        return "redirect:/admin/venues";
    }

    @GetMapping("/admin/reservations")
    public String adminReservations(Model model, HttpSession session) {
        if (!requireAdmin(session)) {
            return "redirect:/login";
        }

        model.addAttribute("venues", spaceService.venues());
        model.addAttribute("reservations", spaceService.recentReservations());
        model.addAttribute("loginUser", session.getAttribute(SESSION_USER_KEY));
        model.addAttribute("loginRole", session.getAttribute(SESSION_ROLE_KEY));
        return "admin-reservations";
    }

    @PostMapping("/admin/reservations")
    public String reserveByAdmin(@RequestParam Long venueId,
                                 @RequestParam String renterName,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startAt,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endAt,
                                 @RequestParam(required = false) String requestMemo,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        if (!requireAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "관리자만 접근 가능합니다.");
            return "redirect:/login";
        }

        spaceService.reserve(venueId, renterName, startAt, endAt, requestMemo);
        redirectAttributes.addFlashAttribute("message", "예약이 등록되었습니다.");
        return "redirect:/admin/reservations";
    }

    @GetMapping("/user/home")
    public String userHome(@RequestParam(required = false) String region, Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        List<SpaceVenue> venues = spaceService.venues();
        List<String> regions = venues.stream()
                .map(SpaceVenue::region)
                .distinct()
                .sorted()
                .toList();

        if (region != null && !region.isBlank()) {
            venues = venues.stream()
                    .filter(v -> region.equals(v.region()))
                    .toList();
        }

        model.addAttribute("venues", venues);
        model.addAttribute("regions", regions);
        model.addAttribute("selectedRegion", region == null ? "" : region);
        model.addAttribute("reservations", spaceService.recentReservations());
        model.addAttribute("loginUser", session.getAttribute(SESSION_USER_KEY));
        model.addAttribute("loginRole", session.getAttribute(SESSION_ROLE_KEY));
        return "user-home";
    }

    @PostMapping("/user/reservations")
    public String reserveByUser(@RequestParam Long venueId,
                                @RequestParam String renterName,
                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startAt,
                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endAt,
                                @RequestParam(required = false) String requestMemo,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        if (!isLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        spaceService.reserve(venueId, renterName, startAt, endAt, requestMemo);
        redirectAttributes.addFlashAttribute("message", "예약이 등록되었습니다.");
        return "redirect:/user/home";
    }

    private boolean requireAdmin(HttpSession session) {
        return isLoggedIn(session) && isAdmin(session);
    }

    private boolean isAdmin(HttpSession session) {
        return "ADMIN".equals(session.getAttribute(SESSION_ROLE_KEY));
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute(SESSION_USER_KEY) != null;
    }
}
