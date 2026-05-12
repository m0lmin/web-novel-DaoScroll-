package com.daoscrolls.core.repository;

import com.daoscrolls.core.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByNovelIdOrderByCreatedAtDesc(Long novelId);
}
