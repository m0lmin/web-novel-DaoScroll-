package com.daoscrolls.core.controller;

import com.daoscrolls.core.entity.Comment;
import com.daoscrolls.core.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/novel/{novelId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long novelId) {
        return ResponseEntity.ok(commentService.getCommentsByNovel(novelId));
    }

    @PostMapping("/novel/{novelId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long novelId, @RequestBody CommentRequest request) {
        log.info("API: Додавання коментаря до новели ID: {}", novelId);
        return ResponseEntity.ok(commentService.addComment(novelId, request.userId(), request.text()));
    }

    // Внутрішній рекорд для зручного парсингу JSON
    public record CommentRequest(Long userId, String text) {}
}
