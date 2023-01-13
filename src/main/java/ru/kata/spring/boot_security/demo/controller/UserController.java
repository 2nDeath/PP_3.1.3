package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        UserDetails user = userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

}
