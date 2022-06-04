package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String registration(ModelMap model) {
        User user = new User();
        model.addAttribute("user1", user);
        return "/admin1";
    }

    @PostMapping("/registration")
    public String addRegistration(@ModelAttribute("user1") @Valid User user) {

        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin")
    public String getAllUsers(Principal principal, ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("admin",
                userService.findUserByEmail(principal.getName()));
        model.addAttribute("user1", new User());
        model.addAttribute("roles", userService.getAllRoles());
        return "/admin1";
    }

    @PostMapping("/admin/{id}")
    public String updateUser(User user) {
        userService.editUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}


