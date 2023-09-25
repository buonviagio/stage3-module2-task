package com.mjc.school.service.dto;

import lombok.Data;

@Data
public class NewsDtoRequest implements DtoRequest{
    private Long id;
    private String name;
    private String content;
    private Long authorId;

    public NewsDtoRequest(Long id, String name, String content, Long authorId) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.authorId = authorId;
    }
}
