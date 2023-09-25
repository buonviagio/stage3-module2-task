package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.ModelMapper;
import com.mjc.school.service.annotations.Validate;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.ErrorCodes;
import com.mjc.school.service.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ImplementNewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {

    private final NewsRepository repository;
    ModelMapper mapper = ModelMapper.INSTANCE;
    @Autowired
    public ImplementNewsService(NewsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return mapper.modelListToDtoListNews(repository.readAll());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        Optional<NewsModel> optional = repository.readById(id);
        if (repository.existById(id) && optional.isPresent()) {
            NewsModel model = optional.get();
            return mapper.modelToDtoNews(model);
        } else {
            throw new NotFoundException(String.format(ErrorCodes.NEW_ID_N_EXIST.toString(), id));
        }
    }
    @Validate
    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        NewsModel newsModel = mapper.dtoToModelNews(createRequest);
        repository.create(newsModel);
        return mapper.modelToDtoNews(newsModel);
    }
    @Validate
    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        if (repository.existById(updateRequest.getId())){
            NewsModel model = mapper.dtoToModelNews(updateRequest);
            NewsModel newsModel = repository.update(model);
            return mapper.modelToDtoNews(newsModel);
        } else {
            throw new NotFoundException(String.format(ErrorCodes.NEW_ID_N_EXIST.toString(), updateRequest.getId()));
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.existById(id)){
            return repository.deleteById(id);
        } else {
            throw new NotFoundException(String.format(ErrorCodes.NEW_ID_N_EXIST.toString(), id));
        }
    }
}
