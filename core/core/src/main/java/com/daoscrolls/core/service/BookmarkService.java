package com.daoscrolls.core.service;

import com.daoscrolls.core.entity.Bookmark;
import com.daoscrolls.core.entity.Chapter;
import com.daoscrolls.core.entity.User;
import com.daoscrolls.core.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserService userService;
    private final ChapterService chapterService;

    public Bookmark addBookmark(Long userId, Long chapterId) {
        log.info("Користувач ID: {} додає закладку на розділ ID: {}", userId, chapterId);

        User user = userService.getUserById(userId);
        Chapter chapter = chapterService.getChapterById(chapterId);

        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setChapter(chapter);

        return bookmarkRepository.save(bookmark);
    }

    public List<Bookmark> getUserBookmarks(Long userId) {
        log.debug("Отримання закладок для користувача ID: {}", userId);
        return bookmarkRepository.findByUserIdOrderBySavedAtDesc(userId);
    }

    public void removeBookmark(Long bookmarkId) {
        log.info("Видалення закладки ID: {}", bookmarkId);
        bookmarkRepository.deleteById(bookmarkId);
    }
}
