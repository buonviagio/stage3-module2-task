package com.mjc.school.operation;

import com.mjc.school.controller.command.*;
import com.mjc.school.exception.CommandNotFoundException;
import com.mjc.school.exception.NotNumberException;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// This class creates objects of the concrete command classes
@Component
public class OperationReader {

    public Command<?> readCommandParameters(Operations operation, Scanner scanner) {
        switch (operation) {
            case GET_ALL_NEWS -> {
                return new GetAllNewsCommand();
            }
            case GET_ALL_AUTHORS -> {
                return new GetAllAutorCommand();
            }
            case GET_NEWS_BY_ID -> {
                return readNewsById(scanner,  operation);
            }
            case REMOVE_NEWS_BY_ID -> {
                return deleteNewsById(scanner,  operation);
            }
            case GET_AUTHOR_BY_ID -> {
                return readAuthorById(scanner, operation);
            }
            case REMOVE_AUTHOR_BY_ID -> {
                return deleteAuthorById(scanner, operation);
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

    private Command<Long>  readNewsById(Scanner scanner, Operations operations) {
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "news");
        return new GetNewsByIdCommand(Long.parseLong(id));
    }

    private Command<Long>  readAuthorById(Scanner scanner, Operations operations) {
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "author");
        return new GetAuthorByIdCommand(Long.parseLong(id));
    }

    private Command<Long>  deleteNewsById(Scanner scanner, Operations operations) {
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "news");
        return new DeleteNewsByIdCommand(Long.parseLong(id));
    }

    private Command<Long>  deleteAuthorById(Scanner scanner, Operations operations) {
        System.out.println("Operation: " + operations.getCommand());
        String id = readNumber(scanner, "author");
        return new DeleteAuthorByIdCommand(Long.parseLong(id));
    }

    private Command<NewsDtoRequest> createNews(Scanner scanner) {
        System.out.println("Operation: Create news.");
        String title = readString(scanner, "news", "title");
        String content = readString(scanner, "news", "content");
        Long authorId =  Long.parseLong(readNumber(scanner, "author"));
        return new CreateNewsCommand(title, content, authorId);
    }

    private Command<AuthorDtoRequest> createAuthor(Scanner scanner) {
        System.out.println("Operation: Create author.");
        String name = readString(scanner, "author", "name");
        return new CreateAuthorCommand(name);
    }

    private Command<NewsDtoRequest> updateNews(Scanner scanner) {
        System.out.println("Operation: Update news.");
        Long newsId =  Long.parseLong(readNumber(scanner, "newsId"));
        String title = readString(scanner, "news", "title");
        String content = readString(scanner, "news", "content");
        Long authorId =  Long.parseLong(readNumber(scanner, "author"));
        return new UpdateNewsCommand(newsId, title, content, authorId);
    }

    private Command<AuthorDtoRequest> updateAuthor(Scanner scanner) {
        System.out.println("Operation: Update author.");
        Map<String, String> map = new HashMap<>();
        Long authorId = Long.parseLong(readNumber(scanner, "author"));
        String name = readString(scanner, "author", "name");
        return new UpdateAuthorCommand(authorId, name);
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

/*
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
*/
