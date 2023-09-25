package com.mjc.school.repository.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsModel implements BaseEntity<Long>{
    private Long id;
    private String name;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;

    public NewsModel(Long id, String name, String content, LocalDateTime createDate, LocalDateTime lastUpdateDate, Long authorId) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.authorId = authorId;
    }

    public NewsModel() {
    }
}
