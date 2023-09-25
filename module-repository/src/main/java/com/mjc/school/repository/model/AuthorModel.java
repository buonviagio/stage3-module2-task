package com.mjc.school.repository.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AuthorModel implements BaseEntity<Long>{
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;

    public AuthorModel(Long id, String name, LocalDateTime createDate, LocalDateTime lastUpdateDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public AuthorModel() {
    }
}
