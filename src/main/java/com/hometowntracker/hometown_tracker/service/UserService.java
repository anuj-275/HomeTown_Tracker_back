package com.hometowntracker.hometown_tracker.service;

import com.hometowntracker.hometown_tracker.model.LocationStatus;
import com.hometowntracker.hometown_tracker.model.User;
import com.hometowntracker.hometown_tracker.repository.UserRepository;
import jakarta.persistence.EnumType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public User register(User user) {
        return repo.save(user);
    }

    public Optional<User> login(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User updateStatus(Long id, LocationStatus status) {
        User user = repo.findById(id).orElseThrow();
        user.setStatus(status);
        return repo.save(user);
    }

    public long countByStatus(LocationStatus status) {
        return repo.findAll().stream()
                .filter(user -> user.getStatus() == status)
                .count();
    }
}
