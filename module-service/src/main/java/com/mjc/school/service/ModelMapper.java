package com.mjc.school.service;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper

public interface ModelMapper {
    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    List<NewsDtoResponse> modelListToDtoListNews(List<NewsModel> newsModelList);

    List<AuthorDtoResponse> modelListToDtoListAuthors(List<AuthorModel> authorModelList);

    /*@Mappings({
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdateDate", ignore = true)
    })*/
    NewsDtoResponse modelToDtoNews(NewsModel baseEntity);

    AuthorDtoResponse modelToDtoAuthor(AuthorModel baseEntity);

    /*@Mappings({
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdateDate", ignore = true)
    })*/
    NewsModel dtoToModelNews(NewsDtoRequest dtoRequest);

    /*@Mappings({
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdateDate", ignore = true)
    })*/
    AuthorModel dtoToModelAuthor(AuthorDtoRequest dtoRequest);
}
