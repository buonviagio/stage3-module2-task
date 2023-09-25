package com.mjc.school.operation;

import com.mjc.school.controller.RequestFromModuleMain;
import com.mjc.school.exception.CommandNotFoundException;
import com.mjc.school.exception.NotNumberException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class OperationReader {
    public RequestFromModuleMain readCommandParameters(Operations operation, Scanner scanner) {
        switch (operation) {
            case GET_ALL_NEWS, GET_ALL_AUTHORS -> {
                return new RequestFromModuleMain();
            }
            case GET_NEWS_BY_ID, REMOVE_NEWS_BY_ID -> {
                return readId(scanner, "news", operation);
            }
            case GET_AUTHOR_BY_ID, REMOVE_AUTHOR_BY_ID -> {
                return readId(scanner, "author", operation);
            }
            case CREATE_NEWS -> {
                return createNews(scanner);
            }
            case CREATE_AUTHOR -> {
                return createAuthor(scanner);
            }
            case UPDATE_NEWS -> {
                return updateNews(scanner);
            }
            case UPDATE_AUTHORS -> {
                return updateAuthor(scanner);
            }
            case EXIT -> System.exit(0);
            default -> throw new CommandNotFoundException("Command not found");
        }
        return null;
    }

    private RequestFromModuleMain readId(Scanner scanner, String param, Operations operations) {
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, param);
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        return new RequestFromModuleMain(map);
    }

    private RequestFromModuleMain createNews(Scanner scanner) {
        System.out.println("Operation: Create news.");
        Map<String, String> map = new HashMap<>();
        return generateNewsData(scanner, map);
    }

    private RequestFromModuleMain createAuthor(Scanner scanner) {
        System.out.println("Operation: Create author.");
        Map<String, String> map = new HashMap<>();
        map.put("name", readString(scanner, "author", "name"));
        return new RequestFromModuleMain(map);
    }

    private RequestFromModuleMain updateNews(Scanner scanner) {
        System.out.println("Operation: Update news.");
        Map<String, String> map = new HashMap<>();
        map.put("id", readNumber(scanner, "news"));
        return generateNewsData(scanner, map);
    }

    private RequestFromModuleMain updateAuthor(Scanner scanner) {
        System.out.println("Operation: Update author.");
        Map<String, String> map = new HashMap<>();
        map.put("id", readNumber(scanner, "author"));
        map.put("name", readString(scanner, "author", "name"));
        return new RequestFromModuleMain(map);
    }

    private RequestFromModuleMain generateNewsData(Scanner scanner, Map<String, String> map) {
        map.put("title", readString(scanner, "news", "title"));
        map.put("content", readString(scanner, "news", "content"));
        map.put("authorId", readNumber(scanner, "author"));
        return new RequestFromModuleMain(map);
    }

    private String readString(Scanner scanner, String param1, String param2) {
        System.out.format("Enter %s %s: ", param1, param2);
        String input;
        do {
            input = scanner.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    private String readNumber(Scanner scanner, String param) {
        System.out.format("Enter %s id: ", param);
        String input;
        do {
            input = scanner.nextLine().trim();
        } while (input.isEmpty());
        if (input.matches("\\d*")) {
            return input;
        } else {
            throw new NotNumberException(String.format("%s should be number", input));
        }
    }
}
