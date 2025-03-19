package com.mjc.school.controller.command;

public abstract class Command<T> {
    public abstract T execute();
}