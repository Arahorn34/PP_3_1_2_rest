package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;

    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/showAll";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", userService.getAllRoles());
        return "admin/new";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "admin/showUser";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user, @RequestParam("roles") Set<Role> roles) {
        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        model.addAttribute("allRoles", userService.getAllRoles());
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute("user") User user) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
