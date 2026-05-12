package com.daoscrolls.core.controller;

import com.daoscrolls.core.entity.Bookmark;
import com.daoscrolls.core.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bookmark>> getUserBookmarks(@PathVariable Long userId) {
        return ResponseEntity.ok(bookmarkService.getUserBookmarks(userId));
    }

    @PostMapping
    public ResponseEntity<Bookmark> addBookmark(@RequestParam Long userId, @RequestParam Long chapterId) {
        return ResponseEntity.ok(bookmarkService.addBookmark(userId, chapterId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBookmark(@PathVariable Long id) {
        bookmarkService.removeBookmark(id);
        return ResponseEntity.ok().build();
    }
}
