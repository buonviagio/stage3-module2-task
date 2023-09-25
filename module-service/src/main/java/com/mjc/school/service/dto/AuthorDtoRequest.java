package com.mjc.school.service.dto;

import lombok.Data;

@Data
public class AuthorDtoRequest implements DtoRequest{
    private Long id;
    private String name;

    public AuthorDtoRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
