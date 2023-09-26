package com.mjc.school.repository.model.data;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuthorData {
    //private static final String AUTHOR_NAMES = "/Users/DimaHeinz/Desktop/test/stage3-module2-task/module-repository/src/main/resources/authors";
    private static final String AUTHOR_NAMES = "authors";
    private static AuthorData authorData;
    private List<AuthorModel> authorlist;
    private Utils utils = Utils.getInstance();

    private AuthorData() {
        init();
    }

    public static AuthorData getAuthorData() {
        if (authorData == null) {
            authorData = new AuthorData();
        }
        return authorData;
    }

    private void init() {
        authorlist = new ArrayList<>();
        for (long i = 1; i <= 20; i++ ){
            LocalDateTime date = utils.getDateTime();
            authorlist.add(new AuthorModel(i, utils.getReadedData(AUTHOR_NAMES), date, date));
        }
    }

    public List<AuthorModel> getAuthorlist() {
        return authorlist;
    }
}
