package com.daoscrolls.core.service;

import com.daoscrolls.core.entity.Comment;
import com.daoscrolls.core.entity.Novel;
import com.daoscrolls.core.entity.User;
import com.daoscrolls.core.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final NovelService novelService;

    public Comment addComment(Long novelId, Long userId, String text) {
        log.info("Користувач ID: {} залишає коментар до новели ID: {}", userId, novelId);

        User user = userService.getUserById(userId);
        Novel novel = novelService.getNovelById(novelId);

        Comment comment = new Comment();
        comment.setText(text);
        comment.setUser(user);
        comment.setNovel(novel);

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByNovel(Long novelId) {
        log.debug("Отримання коментарів для новели ID: {}", novelId);
        return commentRepository.findByNovelIdOrderByCreatedAtDesc(novelId);
    }
}
