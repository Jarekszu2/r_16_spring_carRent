package com.javagda25.securitytemplate.controller;

import com.javagda25.securitytemplate.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user/")
public class AccountController {
    @GetMapping("/register")
    public String registrationForm(Model model, Account account) {
        model.addAttribute("newAccount", account);

        return "registration-form";
    }

    @PostMapping("/register")
    public String register(Account account, String passwordConfirm) {
        // todo: tworzenie konta
        return "redirect:/login";
    }
}
