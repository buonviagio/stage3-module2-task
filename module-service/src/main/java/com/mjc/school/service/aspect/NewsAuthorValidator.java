package com.mjc.school.service.aspect;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exceptions.CheckException;
import com.mjc.school.service.exceptions.ErrorCodes;
import com.mjc.school.service.exceptions.NotFoundException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class NewsAuthorValidator {
    private static final int TITLE_MIN = 5;
    private static final int TITLE_MAX = 30;
    private static final int CONTENT_FIELD_MIN = 5;
    private static final int CONTENT_FIELD_MAX = 255;
    private static final int AUTHOR_MIN = 3;
    private static final int AUTHOR_MAX = 15;
    @Autowired
    BaseRepository<AuthorModel, Long> repository;

    //@Pointcut("execution(* com.mjc.school.service.implementation.ImplementNewsService.create(..))")
    //public void cNews (){}

    @Before("@annotation(com.mjc.school.service.annotations.Validate) " +
            "&& within(com.mjc.school.service.implementation.ImplementNewsService) " +
            "&& args(newsDTO)")
    //@Before("cNews() && args(newsDTO)")
    public void checkNews (NewsDtoRequest newsDTO){
        if (newsDTO == null) {
            throw new CheckException("The field is null ");
        }
            checkString(newsDTO.getName(), "News title",  TITLE_MIN, TITLE_MAX);
            checkString(newsDTO.getContent(), "News content", CONTENT_FIELD_MIN, CONTENT_FIELD_MAX);
            checkAuthorId(newsDTO.getAuthorId());
    }
    @Before("@annotation(com.mjc.school.service.annotations.Validate) " +
            "&& within(com.mjc.school.service.implementation.ImplementAuthorService) " +
            "&& args(authorDTO)")
    public void checkAuthor (AuthorDtoRequest authorDTO){
        if (authorDTO == null) {
            throw new CheckException("The field is null ");
        } else {
            checkString(authorDTO.getName(),"Author name", AUTHOR_MIN, AUTHOR_MAX);
        }
    }

    private void checkString (String value, String param, int min, int max) {
        if (value.length() < min || value.length() > max){
            throw new CheckException(String.format(ErrorCodes.STRING_LANGTH.toString(), param, min, max, param, value));
        }
    }

    private void checkAuthorId (Long id){
        if(id == null) {
            throw new CheckException(String.format(ErrorCodes.NEGATIVE_OR_NULL_NUMBER.toString(), id));
        } else if (!repository.existById(id)){
            throw new NotFoundException(String.format(ErrorCodes.AUTH_ID_NOT_EXIST.toString(), id));
        }
    }
}
