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
import java.util.Map;

@Controller
public class SpaceController {
    private static final String SESSION_USER_KEY = "loginUser";
    private static final Map<String, String> DEMO_USERS = Map.of(
            "admin", "space1234",
            "manager", "welcome123"
    );

    private final SpaceService spaceService;

    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @GetMapping("/")
    public String root(HttpSession session) {
        return isLoggedIn(session) ? "redirect:/dashboard" : "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        if (isLoggedIn(session)) {
            return "redirect:/dashboard";
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
        redirectAttributes.addFlashAttribute("message", "로그인되었습니다.");
        return "redirect:/dashboard";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message", "로그아웃되었습니다.");
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("venues", spaceService.venues());
        model.addAttribute("reservations", spaceService.recentReservations());
        model.addAttribute("venueCount", spaceService.venueCount());
        model.addAttribute("reservationCount", spaceService.reservationCount());
        model.addAttribute("avgHourlyPrice", spaceService.averageHourlyPrice());
        model.addAttribute("loginUser", session.getAttribute(SESSION_USER_KEY));
        return "dashboard";
    }

    @GetMapping("/venues")
    public String venuePage(Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("venues", spaceService.venues());
        model.addAttribute("topVenue", spaceService.highestCapacityVenue().map(SpaceVenue::name).orElse("-"));
        model.addAttribute("loginUser", session.getAttribute(SESSION_USER_KEY));
        return "venues";
    }

    @PostMapping("/venues")
    public String addVenue(@RequestParam String name,
                           @RequestParam String location,
                           @RequestParam int capacity,
                           @RequestParam int hourlyPrice,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        if (!isLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        spaceService.createVenue(name, location, capacity, hourlyPrice);
        redirectAttributes.addFlashAttribute("message", "공간이 등록되었습니다.");
        return "redirect:/venues";
    }

    @GetMapping("/reservations")
    public String reservations(Model model, HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("venues", spaceService.venues());
        model.addAttribute("reservations", spaceService.recentReservations());
        model.addAttribute("loginUser", session.getAttribute(SESSION_USER_KEY));
        return "reservations";
    }

    @PostMapping("/reservations")
    public String reserve(@RequestParam Long venueId,
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
        return "redirect:/reservations";
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute(SESSION_USER_KEY) != null;
    }
}
