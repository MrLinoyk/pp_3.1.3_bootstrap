package ru.kata.spring.boot_security.demo.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String login() {
        return "login";
    }

    @GetMapping("/login")
    public String loginPage (ModelMap modelMap, Principal principal) {
        return "/login";
    }

    @GetMapping(value = "/user")
    public String getUser(Principal principal, ModelMap model) {
        String email = principal.getName();
        model.addAttribute("user", userService.findUserByEmail(email));
        return "user";
    }
}
