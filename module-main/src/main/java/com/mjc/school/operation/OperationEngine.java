package com.mjc.school.operation;

import com.mjc.school.controller.RequestFromModuleMain;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.implementation.Controller;
import com.mjc.school.exception.CommandNotFoundException;
import com.mjc.school.exception.NotNumberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

@Component
public class OperationEngine {
    @Autowired
    private OperationReader operationReader;
    @Autowired
    private Controller controller;

    @Autowired
    public OperationEngine(OperationReader operationReader) {
        this.operationReader = operationReader;
    }

    public void printMenu() {
        System.out.println("Enter the number of operation:");
        for (Operations operations : Operations.values()) {
            System.out.println(operations);
        }
    }

    public void runOperations(String str, Scanner scanner) {
        try {
            Operations operations = Operations.getCommand(str);
            RequestFromModuleMain request = operationReader.readCommandParameters(operations, scanner);
            execute(operations, request);
        } catch (CommandNotFoundException | NotNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    private void execute(Operations operation, RequestFromModuleMain request) {
        Method[] allMethods = Controller.class.getDeclaredMethods();
        Optional<Method> optionalMethod = Arrays.stream(allMethods)
                .filter(x -> x.isAnnotationPresent(CommandHandler.class))
                .filter(x -> x.getAnnotation(CommandHandler.class).operation().equals(operation.getCommandNum()))
                .findFirst();
        if (optionalMethod.isEmpty()) {
            throw new CommandNotFoundException("Command not found");
        } else {
            Method method = optionalMethod.get();
            try {
                System.out.println(
                        method.invoke(controller, request)
                );
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.getCause().getMessage());
            }
        }
    }
}
