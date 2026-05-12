package com.daoscrolls.core.repository;

import com.daoscrolls.core.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUserIdOrderBySavedAtDesc(Long userId);
}
