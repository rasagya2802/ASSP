package com.example.demo3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
public class LoginController {

    // Welcome page (Login)
    @GetMapping("/")
    public String welcome() {
        return "u-login";
    }

    // Login validation
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // Simple validation (replace with database validation later)
        if ("admin".equals(username) && "password".equals(password)) {
            return "redirect:/profile";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "u-login";
        }
    }

    // Profile form
    @GetMapping("/profile")
    public String profileForm(Model model) {
        model.addAttribute("userProfile", new UserProfile());
        return "profile";
    }

    // Profile submission
    @PostMapping("/profile")
    public String submitProfile(@Valid @ModelAttribute UserProfile userProfile, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "profile";
        }
        model.addAttribute("userProfile", userProfile);
        return "profileSuccess";
    }

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard() {
        return "success";
    }

    // Add item to cart
    @PostMapping("/addToCart")
    public String addToCart(@RequestParam String item, Model model) {
        // Add item to cart logic (e.g., store in session or database)
        model.addAttribute("item", item);
        return "success";
    }
}
