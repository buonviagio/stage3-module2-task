package com.mjc.school.controller.command;

public class GetNewsByIdCommand extends Command<Long>{
    private final Long id;

    public GetNewsByIdCommand(Long id) {
        this.id = id;
    }

    @Override
    public Long execute() {
        return id;
    }
}