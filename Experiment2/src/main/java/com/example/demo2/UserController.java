package com.example.demo2;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @GetMapping("/showLogin")
    public String showLoginForm(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/showLogin")
    public String processLoginForm(@Valid User user, BindingResult bindingresult, Model model){
        if(bindingresult.hasErrors()){
            return "login";
        }

        if("admin".equals(user.getUsername())&&"password".equals(user.getPassword())){
            model.addAttribute("message", "Login successful");
            return "success";
        } else{
            model.addAttribute("error","Invalid username or password");
            return "login";
        }
    }
}