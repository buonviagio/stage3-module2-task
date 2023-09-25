package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.AuthorRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.ModelMapper;
import com.mjc.school.service.annotations.Validate;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exceptions.ErrorCodes;
import com.mjc.school.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ImplementAuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final AuthorRepository repository;

    @Autowired
    public ImplementAuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    ModelMapper mapper = ModelMapper.INSTANCE;

    @Override
    public List<AuthorDtoResponse> readAll() {
        return mapper.modelListToDtoListAuthors(repository.readAll());
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        Optional<AuthorModel> optional = repository.readById(id);
        if (repository.existById(id) && optional.isPresent()) {
            AuthorModel model = optional.get();
            return mapper.modelToDtoAuthor(model);
        } else {
            throw new NotFoundException(String.format(ErrorCodes.AUTH_ID_NOT_EXIST.toString(), id));
        }
    }

    @Validate
    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        AuthorModel authorModel = mapper.dtoToModelAuthor(createRequest);
        repository.create(authorModel);
        return mapper.modelToDtoAuthor(authorModel);
    }

    @Validate
    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        if (repository.existById(updateRequest.getId())) {
            AuthorModel model = mapper.dtoToModelAuthor(updateRequest);
            AuthorModel authorModel = repository.update(model);
            return mapper.modelToDtoAuthor(authorModel);
        } else {
            throw new NotFoundException(String.format(ErrorCodes.AUTH_ID_NOT_EXIST.toString(), updateRequest.getId()));
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.deleteById(id)) {
            return true;
        } else {
            throw new NotFoundException(String.format(ErrorCodes.AUTH_ID_NOT_EXIST.toString(), id));
        }
    }

}
