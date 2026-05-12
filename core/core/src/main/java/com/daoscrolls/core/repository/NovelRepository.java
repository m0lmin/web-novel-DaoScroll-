package com.daoscrolls.core.repository;

import com.daoscrolls.core.entity.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NovelRepository extends JpaRepository<Novel, Long> {
    // Цей метод критично важливий для нашого динамічного пошуку на Frontend (JS Fetch)
    List<Novel> findByTitleContainingIgnoreCase(String title);
    List<Novel> findByAuthorId(Long authorId);
}
