package com.mjc.school.controller.command;

import com.mjc.school.service.dto.AuthorDtoRequest;

public class CreateAuthorCommand extends Command<AuthorDtoRequest> {
    private final AuthorDtoRequest author;

    public CreateAuthorCommand(String name) {
        author = new AuthorDtoRequest(0L, name);
    }

    @Override
    public AuthorDtoRequest execute() {
        return author;
    }
}
