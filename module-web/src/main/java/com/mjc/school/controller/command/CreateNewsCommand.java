package com.mjc.school.controller.command;
import com.mjc.school.service.dto.NewsDtoRequest;
import lombok.Getter;

@Getter
public class CreateNewsCommand extends Command<NewsDtoRequest>{

    NewsDtoRequest n;

    public CreateNewsCommand(String title, String content, Long authorId) {
        n = new NewsDtoRequest(0L, title, content, authorId);
    }

    @Override
    public NewsDtoRequest execute() {
        return n;
    }
}