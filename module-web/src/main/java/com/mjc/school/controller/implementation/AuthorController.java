package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.annotations.CommandParam;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService;
    @Autowired
    public AuthorController(BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService) {
        this.authorService = authorService;
        System.out.println("AuthorController создан");
    }

    @CommandHandler(operation = "Get all authors")
    @Override
    public List<AuthorDtoResponse> readAll() {
        List<AuthorDtoResponse> responses = authorService.readAll();
        System.out.println(printAuthor(responses.toArray(new AuthorDtoResponse[0])));
        return responses;
        //return authorService.readAll();
    }

    @CommandHandler(operation = "Get author by id")
    @Override
    public AuthorDtoResponse readById(@CommandParam("id") Long id) {
        AuthorDtoResponse response = authorService.readById(id);
        System.out.println(printAuthor(response));
        return response;
        //return authorService.readById(id);
    }

    @CommandHandler(operation = "Create author")
    @Override
    public AuthorDtoResponse create(@CommandBody AuthorDtoRequest createRequest) {
        AuthorDtoResponse response = authorService.create(createRequest);
        System.out.println(printAuthor(response));
        return response;
        //return authorService.create(createRequest);
    }

    @CommandHandler(operation = "Update authors")
    @Override
    public AuthorDtoResponse update(@CommandBody AuthorDtoRequest updateRequest) {
        AuthorDtoResponse response = authorService.update(updateRequest);
        System.out.println(printAuthor(response));
        return response;
        //return authorService.update(updateRequest);
    }

    @CommandHandler(operation = "Remove author by id")
    @Override
    public boolean deleteById(@CommandParam("id") Long id) {
        boolean response = authorService.deleteById(id);
        System.out.println("Author was deleted: " + response);
        return response;
        //return authorService.deleteById(id);
    }

    private String printAuthor(AuthorDtoResponse... response) {
        StringBuilder builder = new StringBuilder();
        for (AuthorDtoResponse n : response) {
            builder.append("AuthorDtoResponse [id=").append(n.getId())
                    .append(", name=").append(n.getName())
                    .append(", createDate=").append(n.getCreateDate())
                    .append(", lastUpdatedDate=").append(n.getLastUpdateDate())
                    .append("]").append("\n");
        }
        return builder.toString();
    }
}

/*package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.implementation.ImplementAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService;
    @Autowired
    public AuthorController(BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService) {
        this.authorService = authorService;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return authorService.readAll();
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        return authorService.readById(id);
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        return authorService.create(createRequest);
    }

    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        return authorService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return authorService.deleteById(id);
    }
}*/
