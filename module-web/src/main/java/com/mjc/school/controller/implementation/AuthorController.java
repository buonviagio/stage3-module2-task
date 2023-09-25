package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.implementation.ImplementAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {
    @Autowired
    public AuthorController(ImplementAuthorService authorService) {
        this.authorService = authorService;
    }

    private final ImplementAuthorService authorService;
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
}
