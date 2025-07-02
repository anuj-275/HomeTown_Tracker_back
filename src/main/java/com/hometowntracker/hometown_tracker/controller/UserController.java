package com.hometowntracker.hometown_tracker.controller;

import com.hometowntracker.hometown_tracker.model.LocationStatus;
import com.hometowntracker.hometown_tracker.model.User;
import com.hometowntracker.hometown_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public Optional<User> login(@RequestBody Map<String, String> payload) {
        return service.login(payload.get("email"), payload.get("password"));
    }

    @PutMapping("/status/{id}")
public User updateStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
    User user = userRepository.findById(id).orElseThrow();
    user.setStatus(LocationStatus.valueOf(payload.get("status")));
    user.setCity(payload.getOrDefault("city", "Gaya"));
    return userRepository.save(user);
}


    @GetMapping("/users")
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/summary")
    public Map<String, Long> summary() {
        long inTown = service.countByStatus(LocationStatus.IN_HOMETOWN);
        long outTown = service.countByStatus(LocationStatus.OUT_OF_TOWN);
        Map<String, Long> map = new HashMap<>();
        map.put("inHometown", inTown);
        map.put("outOfTown", outTown);
        return map;
    }
}
