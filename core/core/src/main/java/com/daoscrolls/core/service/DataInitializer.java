package com.daoscrolls.core.service;

import com.daoscrolls.core.entity.Chapter;
import com.daoscrolls.core.entity.Novel;
import com.daoscrolls.core.entity.User;
import com.daoscrolls.core.repository.ChapterRepository;
import com.daoscrolls.core.repository.NovelRepository;
import com.daoscrolls.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final NovelRepository novelRepository;
    private final ChapterRepository chapterRepository;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            log.info("База порожня. Починаю масштабну генерацію Дао-світу...");

            // 1. Створюємо Адміна
            User admin = new User();
            admin.setUsername("Admin");
            admin.setEmail("admin@dao.com");
            admin.setPassword("123");
            admin.setRole("ROLE_ADMIN");
            User savedAdmin = userRepository.save(admin);

            // Словники для генерації
            String[] adjectives = {"Небесний", "Кривавий", "Божественний", "Темний", "Безсмертний", "Золотий", "Срібний", "Забутий"};
            String[] nouns = {"Дракон", "Меч", "Шлях", "Лотос", "Імператор", "Сувій", "Дух", "Демон"};
            String[] genres = {"Xianxia", "Wuxia", "Xuanhuan", "LitRPG"};

            // Словники для назв розділів
            String[] chActions = {"Пробудження", "Пошук", "Битва за", "Зрада", "Таємниця", "Зустріч біля", "Гнів", "Осяяння"};
            String[] chSubjects = {"Духовного Кореня", "Старійшини Секти", "Небесного Полум'я", "Лісу Демонів", "Забутого Артефакту", "Кривавого Лотоса"};

            Random random = new Random();

            // 2. Генеруємо 100 новел
            for (int i = 1; i <= 100; i++) {
                Novel novel = new Novel();
                String title = adjectives[random.nextInt(adjectives.length)] + " " + nouns[random.nextInt(nouns.length)] + " " + i;
                novel.setTitle(title);
                novel.setGenre(genres[random.nextInt(genres.length)]);

                // Генеруємо обкладинку (випадкова картинка)
                novel.setCoverUrl("https://picsum.photos/seed/dao" + i + "/400/600");

                // Генеруємо епічний пролог
                novel.setDescription("У стародавні часи, коли небеса ще були єдині з землею, народилася легенда. Сили темряви почали проникати у світ смертних, руйнуючи баланс Інь та Ян. Багато могутніх практиків намагалися зупинити цей хаос, але всі вони зазнали поразки. І лише '" + title + "' має потенціал змінити хід історії. Цей шлях буде сповнений болю, втрат та неймовірних відкриттів. Чи зможе герой подолати своїх внутрішніх демонів та досягти безсмертя? Відповідь лежить за межами відомого світу...");

                novel.setAuthor(savedAdmin);
                Novel savedNovel = novelRepository.save(novel);

                // 3. Генеруємо розділи
                // random.nextInt(29) генерує число від 0 до 28. Додаємо 7 -> отримуємо від 7 до 35!
                int chapterCount = random.nextInt(29) + 7;

                List<Chapter> chapters = new ArrayList<>();
                for (int j = 1; j <= chapterCount; j++) {
                    Chapter ch = new Chapter();
                    ch.setChapterNumber(j);
                    ch.setTitle(chActions[random.nextInt(chActions.length)] + " " + chSubjects[random.nextInt(chSubjects.length)]);

                    ch.setContent("Енергія Ці вирувала у просторі, коли герой ступив на новий шлях. Його меридіани пульсували від напруги.\n" +
                            "Минали дні і ночі медитації. Цей розділ №" + j + " описує, як він крок за кроком наближався до розуміння Великого Дао...");
                    ch.setNovel(savedNovel);
                    chapters.add(ch);
                }

                // Зберігаємо всі розділи книги
                chapterRepository.saveAll(chapters);

                if (i % 20 == 0) log.info("Згенеровано {} новел...", i);
            }
            log.info("Успіх! Створено 100 новел (кожна має від 7 до 35 розділів)!");
        }
    }
}