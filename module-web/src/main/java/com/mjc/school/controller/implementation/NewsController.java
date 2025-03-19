package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.annotations.CommandParam;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
// This class is Receiver of commands.
@Component
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {

    private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;
    @Autowired
    public NewsController(BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService) {
        this.newsService = newsService;
        System.out.println("NewsController создан");
    }

    @CommandHandler(operation = "Get all news")
    @Override
    public List<NewsDtoResponse> readAll() {
        List<NewsDtoResponse> response = newsService.readAll();
        System.out.println(printNews(newsService.readAll().toArray(new NewsDtoResponse[0])));
        return response;
    }

    @CommandHandler(operation = "Get news by id")
    @Override
    public NewsDtoResponse readById(@CommandParam("id") Long id) {
        NewsDtoResponse response = newsService.readById(id);
        System.out.println(printNews(response));
        return response;
    }

    @CommandHandler(operation = "Create news")
    @Override
    public NewsDtoResponse create(@CommandBody NewsDtoRequest createRequest) {
        NewsDtoResponse response = newsService.create(createRequest);
        System.out.println(printNews(response));
        return response;
    }

    @CommandHandler(operation = "Update news")
    @Override
    public NewsDtoResponse update(@CommandBody NewsDtoRequest updateRequest) {
        NewsDtoResponse response = newsService.update(updateRequest);
        System.out.println(printNews(response));
        return response;
    }

    @CommandHandler(operation = "Remove news by id")
    @Override
    public boolean deleteById(@CommandParam("id") Long id) {
        return newsService.deleteById(id);
    }

    private String printNews(NewsDtoResponse... response) {
        StringBuilder builder = new StringBuilder();
        for (NewsDtoResponse n : response) {
            builder.append("NewsDtoResponse [id=").append(n.getId())
                    .append(", title=").append(n.getName())
                    .append(", content=").append(n.getContent())
                    .append(", createDate=").append(n.getCreateDate())
                    .append(", lastUpdatedDate=").append(n.getLastUpdateDate())
                    .append(", authorId=").append(n.getAuthorId()).append("]")
                    .append("\n");
        }
        return builder.toString();
    }
}

/*
package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.CheckException;
import com.mjc.school.service.implementation.ImplementNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {

    private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;
    @Autowired
    public NewsController(BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService) {
        this.newsService = newsService;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsService.readAll();
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        return newsService.readById(id);
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        return newsService.create(createRequest);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        return newsService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return newsService.deleteById(id);
    }
}
*/
