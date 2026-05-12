package com.daoscrolls.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "novels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Novel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String coverUrl;

    @Column(nullable = false)
    private String genre; // Жанри, наприклад: Xianxia, Wuxia, Xuanhuan, Fantasy

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private User author; // Автора підтягуємо разом із книгою

    @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Щоб не вантажити всі розділи при простому перегляді каталогу
    private List<Chapter> chapters = new ArrayList<>();

    @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();
}
