package com.daoscrolls.core.controller;

import com.daoscrolls.core.entity.Chapter;
import com.daoscrolls.core.service.ChapterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class ChapterController {

    private final ChapterService chapterService;

    @GetMapping("/novel/{novelId}")
    public ResponseEntity<List<Chapter>> getChaptersForNovel(@PathVariable Long novelId) {
        return ResponseEntity.ok(chapterService.getChaptersByNovel(novelId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chapter> getChapter(@PathVariable Long id) {
        return ResponseEntity.ok(chapterService.getChapterById(id));
    }

    @PostMapping("/novel/{novelId}")
    public ResponseEntity<Chapter> addChapter(@PathVariable Long novelId, @RequestBody Chapter chapter) {
        log.info("API: Додавання розділу до новели ID: {}", novelId);
        return ResponseEntity.ok(chapterService.addChapter(novelId, chapter));
    }
}
