package com.mjc.school.operation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.annotations.CommandParam;
import com.mjc.school.controller.command.Command;
import com.mjc.school.exception.CommandNotFoundException;
import com.mjc.school.exception.NotNumberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

// This class Invoker of the commands (remote control)
@Component
public class OperationEngine {
    @Autowired
    private OperationReader operationReader;
    private final List<BaseController<?, ?, ?>> controllers;

    @Autowired
    public OperationEngine(OperationReader operationReader, List<BaseController<?, ?, ?>> controllers) {

        this.operationReader = operationReader;
        this.controllers = controllers;
        System.out.println("Controllers list: " + controllers);

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
            //System.out.println("operations ====> " + operations);
            Command<?> command = operationReader.readCommandParameters(operations, scanner);
            execute(operations, command);
        } catch (CommandNotFoundException | NotNumberException e) {
            System.out.println(e.getMessage());
        }
    }


    private void execute(Operations operation, Command<?> command) {
        boolean methodFound = false;
        for (BaseController<?, ?, ?> controller : controllers) {

            Optional<Method> optionalMethod = Arrays.stream(controller.getClass().getDeclaredMethods())
                    .filter(x -> x.isAnnotationPresent(CommandHandler.class))
                    .filter(x -> x.getAnnotation(CommandHandler.class).operation().equals(operation.getCommand()))
                    .findFirst();

            if (optionalMethod.isPresent()) {
                Method method = optionalMethod.get();

                try {
                    Object[] args = prepareArguments(method, command);
                    // at this point we receive return from methods from classes AuthorController and NewsController
                    method.invoke(controller, args);
                    methodFound = true;
                    break;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println(e.getCause().getMessage());
                }
            }
        }
        if (!methodFound) {
            throw new CommandNotFoundException("Command not found, we do not perform such operation");
        }
    }

    private Object[] prepareArguments(Method method, Command<?> command) {
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(CommandBody.class)) {
                args[i] = command.execute();
            } else if (parameters[i].isAnnotationPresent(CommandParam.class)) {
                args[i] = extractCommandParam(command, parameters[i].getType());
            }
        }

        return args;
    }

    private Object extractCommandParam(Command<?> command, Class<?> paramType) {
        if (paramType.equals(Long.class)) {
            return command.execute();
        }
        return null;
    }
}


/*package com.mjc.school.operation;

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
*/