package com.daoscrolls.core.service;

import com.daoscrolls.core.entity.Novel;
import com.daoscrolls.core.entity.User;
import com.daoscrolls.core.repository.NovelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NovelService {

    private final NovelRepository novelRepository;
    private final UserService userService;

    public Novel createNovel(Novel novel, Long authorId) {
        log.info("Створення нової новели: '{}'", novel.getTitle());
        User author = userService.getUserById(authorId);
        novel.setAuthor(author);
        return novelRepository.save(novel);
    }

    public List<Novel> getAllNovels() {
        log.debug("Отримання списку всіх новел");
        return novelRepository.findAll();
    }

    public Novel getNovelById(Long id) {
        return novelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Novel not found"));
    }

    // Метод для динамічного пошуку з фронтенду
    public List<Novel> searchNovels(String query) {
        log.info("Пошук новел за запитом: '{}'", query);
        if (query == null || query.trim().isEmpty()) {
            return novelRepository.findAll();
        }
        return novelRepository.findByTitleContainingIgnoreCase(query.trim());
    }
}
