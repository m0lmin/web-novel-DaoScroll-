package com.daoscrolls.core.service;

import com.daoscrolls.core.entity.User;
import com.daoscrolls.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(User user) {
        log.info("Спроба реєстрації нового користувача: {}", user.getUsername());
        if (userRepository.existsByUsername(user.getUsername())) {
            log.warn("Користувач з іменем {} вже існує", user.getUsername());
            throw new RuntimeException("Username already taken");
        }
        // У реальному проєкті тут має бути хешування пароля (напр., BCryptPasswordEncoder)
        user.setRole("ROLE_READER");
        User savedUser = userRepository.save(user);
        log.info("Користувача {} успішно зареєстровано з ID: {}", savedUser.getUsername(), savedUser.getId());
        return savedUser;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User loginUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new RuntimeException("Невірний логін або пароль"));
    }
    public User addExperience(Long userId, int amount) {
        User user = getUserById(userId);
        if (user.getLevel() >= 15) return user; // Максимальний рівень - Небесний Імператор

        user.setExperience(user.getExperience() + amount);

        // Щоб перейти на наступний рівень, треба (Рівень * 100) досвіду
        // Наприклад: 1->2 (100 Ци), 2->3 (200 Ци) і т.д.
        int requiredExp = user.getLevel() * 100;
        while (user.getExperience() >= requiredExp && user.getLevel() < 15) {
            user.setExperience(user.getExperience() - requiredExp);
            user.setLevel(user.getLevel() + 1);
            requiredExp = user.getLevel() * 100;
        }
        return userRepository.save(user);
    }
}
