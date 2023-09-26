package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mjc.school.controller.RequestFromModuleMain;

import java.util.Map;

@Component
public class Controller {
    @Autowired
    private BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;
    //private AuthorController authorController;
    @Autowired
    private BaseController<NewsDtoRequest, NewsDtoResponse, Long> newsController;
    //private NewsController newsController;

    @CommandHandler(operation = "1")
    public String getNews(RequestFromModuleMain request) {
        return printNews(newsController.readAll().toArray(new NewsDtoResponse[0]));

    }


    @CommandHandler(operation = "3")
    public String getNewsById(RequestFromModuleMain request) {
        return printNews(newsController.readById(Long.parseLong(request.getParams().get("id"))));
    }

    @CommandHandler(operation = "5")
    public String addNews(RequestFromModuleMain request) {
        Map<String, String> m = request.getParams();
        NewsDtoRequest n = new NewsDtoRequest(0L, m.get("title"), m.get("content"), Long.parseLong(m.get("authorId")));
        return printNews(newsController.create(n));
    }

    @CommandHandler(operation = "7")
    public String updateNews(RequestFromModuleMain request) {
        Map<String, String> m = request.getParams();
        NewsDtoRequest n = new NewsDtoRequest(0L, m.get("title"), m.get("content"), Long.parseLong(m.get("authorId")));
        return printNews(newsController.update(n));
    }

    @CommandHandler(operation = "9")
    public boolean deleteNews(RequestFromModuleMain request) {
        return newsController.deleteById(Long.parseLong(request.getParams().get("id")));
    }

    @CommandHandler(operation = "2")
    public String getAuthors(RequestFromModuleMain request) {
        return printAuthor(authorController.readAll().toArray(new AuthorDtoResponse[0]));
    }

    @CommandHandler(operation = "4")
    public String getAuthorById(RequestFromModuleMain request) {
        return printAuthor(authorController.readById(Long.parseLong(request.getParams().get("id"))));
    }

    @CommandHandler(operation = "6")
    public String addAuthor(RequestFromModuleMain request) {
        Map<String, String> m = request.getParams();
        AuthorDtoRequest n = new AuthorDtoRequest(0L, m.get("name"));
        return printAuthor(authorController.create(n));
    }

    @CommandHandler(operation = "8")
    public String updateAuthor(RequestFromModuleMain request) {
        Map<String, String> m = request.getParams();
        AuthorDtoRequest n = new AuthorDtoRequest(0L, m.get("name"));
        return printAuthor(authorController.update(n));
    }

    @CommandHandler(operation = "10")
    public boolean deleteAuthor(RequestFromModuleMain request) {
        return authorController.deleteById(Long.parseLong(request.getParams().get("id")));
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
