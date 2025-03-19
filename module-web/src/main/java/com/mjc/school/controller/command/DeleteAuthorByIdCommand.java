package com.mjc.school.controller.command;

public class DeleteAuthorByIdCommand extends Command<Long>{
    private final Long id;

    public DeleteAuthorByIdCommand(Long id) {
        this.id = id;
    }

    @Override
    public Long execute() {
        return id;
    }
}