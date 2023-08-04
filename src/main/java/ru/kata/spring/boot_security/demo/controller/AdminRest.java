package ru.kata.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRest {
    private final UserService userService;

    public AdminRest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.showUser(id);
    }

    @PostMapping()
    public User saveUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @PutMapping()
    public User editUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.delete(id);
        return "ok";
    }

}
