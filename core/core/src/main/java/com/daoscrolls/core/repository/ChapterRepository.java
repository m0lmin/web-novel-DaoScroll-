package com.daoscrolls.core.repository;

import com.daoscrolls.core.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    List<Chapter> findByNovelIdOrderByChapterNumberAsc(Long novelId);
}
