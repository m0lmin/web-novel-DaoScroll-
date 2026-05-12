package com.daoscrolls.core.controller;

import com.daoscrolls.core.entity.Novel;
import com.daoscrolls.core.service.NovelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/novels")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class NovelController {

    private final NovelService novelService;

    @GetMapping
    public ResponseEntity<List<Novel>> getAllNovels() {
        return ResponseEntity.ok(novelService.getAllNovels());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Novel>> searchNovels(@RequestParam String query) {
        log.info("API: Пошук новел за запитом '{}'", query);
        return ResponseEntity.ok(novelService.searchNovels(query));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Novel> getNovel(@PathVariable Long id) {
        return ResponseEntity.ok(novelService.getNovelById(id));
    }

    @PostMapping
    public ResponseEntity<Novel> createNovel(@RequestBody Novel novel, @RequestParam Long authorId) {
        log.info("API: Запит на створення новели від автора ID: {}", authorId);
        return ResponseEntity.ok(novelService.createNovel(novel, authorId));
    }
}
