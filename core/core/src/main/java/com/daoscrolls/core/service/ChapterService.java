package com.daoscrolls.core.service;

import com.daoscrolls.core.entity.Chapter;
import com.daoscrolls.core.entity.Novel;
import com.daoscrolls.core.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChapterService {

    private final ChapterRepository chapterRepository;
    private final NovelService novelService;

    public Chapter addChapter(Long novelId, Chapter chapter) {
        log.info("Додавання розділу {} до новели з ID: {}", chapter.getChapterNumber(), novelId);
        Novel novel = novelService.getNovelById(novelId);
        chapter.setNovel(novel);
        return chapterRepository.save(chapter);
    }

    public List<Chapter> getChaptersByNovel(Long novelId) {
        log.debug("Отримання всіх розділів для новели з ID: {}", novelId);
        return chapterRepository.findByNovelIdOrderByChapterNumberAsc(novelId);
    }

    public Chapter getChapterById(Long id) {
        return chapterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));
    }
}
