package com.mjc.school.controller.command;

public class DeleteNewsByIdCommand extends Command<Long>{
    private final Long id;

    public DeleteNewsByIdCommand(Long id) {
        this.id = id;
    }

    @Override
    public Long execute() {
        return id;
    }
}