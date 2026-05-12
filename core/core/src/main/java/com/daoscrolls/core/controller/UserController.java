package com.daoscrolls.core.controller;

import com.daoscrolls.core.entity.User;
import com.daoscrolls.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Дозволяє запити з будь-якого Frontend
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        log.info("API: Запит на реєстрацію користувача {}", user.getUsername());
        return ResponseEntity.ok(userService.registerUser(user));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        try {
            User user = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
            log.info("Користувач {} успішно увійшов", user.getUsername());
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserProfile(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/{id}/exp")
    public ResponseEntity<User> addExp(@PathVariable Long id, @RequestParam int amount) {
        log.info("Нарахування {} Ци для користувача ID: {}", amount, id);
        return ResponseEntity.ok(userService.addExperience(id, amount));
    }
}
