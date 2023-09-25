package com.mjc.school.service.dto;

import java.time.LocalDateTime;

public interface DtoResponse {
    Long getId();
    void setId(Long id);
    String getName();
    void setName(String name);
    LocalDateTime getCreateDate();
    void setCreateDate(LocalDateTime createDate);
    LocalDateTime getLastUpdateDate ();
    void setLastUpdateDate(LocalDateTime lastUpdateDate);
}
