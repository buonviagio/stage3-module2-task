package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    private final DataSource dataSource;

    public NewsRepository() {
        dataSource = DataSource.getInstance();
    }

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNews();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return dataSource.getNews().stream().filter(n -> Objects.equals(n.getId(), id)).findFirst();
    }

    @Override
    public NewsModel create(NewsModel entity) {
        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        List<NewsModel> list = dataSource.getNews();
        list.sort(Comparator.comparing(NewsModel::getId));
        entity.setId(list.get(list.size() - 1).getId() + 1L);
        entity.setCreateDate(time);
        entity.setLastUpdateDate(time);
        //entity.setId(list.size() + 1L);
        list.add(entity);
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        NewsModel newsModel;
        Optional<NewsModel> optional = readById(entity.getId());
        if (optional.isPresent()) {
            newsModel = optional.get();
            newsModel.setName(entity.getName());
            newsModel.setContent(entity.getContent());
            newsModel.setAuthorId(entity.getAuthorId());
            newsModel.setLastUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            return newsModel;
        } else {
            return optional.orElse(new NewsModel());
        }

    }

    @Override
    public boolean deleteById(Long id) {
        Optional<NewsModel> optional = readById(id);
        if (optional.isPresent()) {
            List<NewsModel> list = dataSource.getNews();
            return list.remove(optional.get());
        } else {
            return false;
        }
        /*
        List<NewsModel> list = dataSource.getNews();
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i).getId(), id)) {
                list.remove(i);
                return true;
            }
        }
        return false;
         */
    }

    @Override
    public boolean existById(Long id) {
        return dataSource.getNews().stream().anyMatch(news -> id.equals(news.getId()));
    }
}
