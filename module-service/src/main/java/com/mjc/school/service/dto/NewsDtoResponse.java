package com.mjc.school.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class NewsDtoResponse implements DtoResponse{
    private Long id;
    private String name;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;

}
