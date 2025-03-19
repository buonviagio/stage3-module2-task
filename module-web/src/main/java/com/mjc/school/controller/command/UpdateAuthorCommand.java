package com.mjc.school.controller.command;

import com.mjc.school.service.dto.AuthorDtoRequest;

public class UpdateAuthorCommand extends Command<AuthorDtoRequest>{
    AuthorDtoRequest author;

    public UpdateAuthorCommand(Long authorId,String name) {
        author = new AuthorDtoRequest(authorId, name);
    }

    @Override
    public AuthorDtoRequest execute() {
        return author;
    }
}