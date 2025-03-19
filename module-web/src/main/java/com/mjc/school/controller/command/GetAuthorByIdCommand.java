package com.mjc.school.controller.command;


public class GetAuthorByIdCommand extends Command<Long>{

    private final Long id;

    public GetAuthorByIdCommand(Long id) {
        this.id = id;
    }

    @Override
    public Long execute() {
        return id;
    }
}