package com.mjc.school.repository.model.data;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsData {
    //private static final String CONTENT_FILE_NAME = "/Users/DimaHeinz/Desktop/test/stage3-module2-task/module-repository/src/main/resources/news";
    private static final String CONTENT_FILE_NAME = "news";
    //private static final String NEWS_FILE_NAME = "/Users/DimaHeinz/Desktop/test/stage3-module2-task/module-repository/src/main/resources/content";
    private static final String NEWS_FILE_NAME = "content";
    private static NewsData newsData;
    private List<NewsModel> newsList;
    private Utils utils = Utils.getInstance();

    private NewsData(List<AuthorModel> authorModelList) {
        init(authorModelList);
    }

    public static NewsData getNewsData(List<AuthorModel> authorModels) {
        if (newsData == null) {
            newsData = new NewsData(authorModels);
        }
        return newsData;
    }

    private void init(List<AuthorModel> authorModelList) {
        newsList = new ArrayList<>();
        Random random = new Random();
        for (long i = 1; i <= 20; i++){
            LocalDateTime date = utils.getDateTime();
            newsList.add(new NewsModel(i,
                    utils.getReadedData(CONTENT_FILE_NAME),
                    utils.getReadedData(NEWS_FILE_NAME),
                    date,
                    date,
                    authorModelList.get(random.nextInt(authorModelList.size())).getId()));
        }
    }

    public List<NewsModel> getNewsList() {
        return newsList;
    }
}
