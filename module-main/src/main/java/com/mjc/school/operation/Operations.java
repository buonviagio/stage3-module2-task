package com.mjc.school.operation;

import com.mjc.school.exception.CommandNotFoundException;

public enum Operations {
    GET_ALL_NEWS("Get all news", "1"),
    GET_ALL_AUTHORS("Get all authors.", "2"),
    GET_NEWS_BY_ID("Get news by id.", "3"),
    GET_AUTHOR_BY_ID("Get author by id.", "4"),
    CREATE_NEWS("Create news.", "5"),
    CREATE_AUTHOR("Create author.", "6"),
    UPDATE_NEWS("Update news.", "7"),
    UPDATE_AUTHORS("Update authors.", "8"),
    REMOVE_NEWS_BY_ID("Remove news by id.", "9"),
    REMOVE_AUTHOR_BY_ID("Remove author by id.", "10"),
    EXIT("Exit", "0");

    private String command;
    private String commandNum;

    Operations(String command, String commandNum) {
        this.command = command;
        this.commandNum = commandNum;
    }

    public static Operations getCommand(String number) {
        for (Operations operation : Operations.values()){
            if(operation.commandNum.equals(number)){
                return operation;
            }
        }
        throw new CommandNotFoundException("Command not found");
    }

    public String getCommand() {
        return command;
    }

    public String getCommandNum() {
        return commandNum;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", commandNum, command);
    }
}
