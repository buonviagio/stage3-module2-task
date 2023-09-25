package com.mjc.school.repository.utils;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.mjc.school.repository.model.data.AuthorData.getAuthorData;
import static com.mjc.school.repository.model.data.NewsData.getNewsData;
@Component
public class DataSource {
    private List<NewsModel> newsList;
    private List<AuthorModel> authorList;
    private static DataSource dataSource;

    private DataSource() {
        authorList = getAuthorData().getAuthorlist();
        newsList = getNewsData(authorList).getNewsList();
    }

    public static DataSource getInstance() {
        if (dataSource == null) {
            dataSource = new DataSource();
        }
        return dataSource;
    }

    public List<NewsModel> getNews() {
        return newsList;
    }
    public List<AuthorModel> getAuthors() {
        return authorList;
    }
}
