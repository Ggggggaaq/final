package com.example.spacedev.controller;

import com.example.spacedev.service.SpaceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class SpaceController {
    private final SpaceService spaceService;

    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("venues", spaceService.venues());
        model.addAttribute("reservations", spaceService.recentReservations());
        return "home";
    }

    @PostMapping("/venues")
    public String addVenue(@RequestParam String name,
                           @RequestParam String location,
                           @RequestParam int capacity,
                           @RequestParam int hourlyPrice,
                           RedirectAttributes redirectAttributes) {
        spaceService.createVenue(name, location, capacity, hourlyPrice);
        redirectAttributes.addFlashAttribute("message", "공간이 등록되었습니다.");
        return "redirect:/";
    }

    @PostMapping("/reservations")
    public String reserve(@RequestParam Long venueId,
                          @RequestParam String renterName,
                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startAt,
                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endAt,
                          @RequestParam(required = false) String requestMemo,
                          RedirectAttributes redirectAttributes) {
        spaceService.reserve(venueId, renterName, startAt, endAt, requestMemo);
        redirectAttributes.addFlashAttribute("message", "예약이 등록되었습니다.");
        return "redirect:/";
    }
}
