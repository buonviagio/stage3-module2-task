package com.mjc.school.controller.command;

import com.mjc.school.service.dto.NewsDtoRequest;

public class UpdateNewsCommand extends Command<NewsDtoRequest>{
    NewsDtoRequest n;

    public UpdateNewsCommand(Long newsId,String title, String content, Long authorId) {
        n = new NewsDtoRequest(newsId, title, content, authorId);
    }

    @Override
    public NewsDtoRequest execute() {
        return n;
    }
}