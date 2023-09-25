package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.annotations.DeleteNews;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {
    private DataSource dataSource;

    public AuthorRepository() {
        dataSource = DataSource.getInstance();
    }

    @Override
    public List<AuthorModel> readAll() {
        return dataSource.getAuthors();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return dataSource.getAuthors().stream().filter(n -> Objects.equals(n.getId(), id)).findFirst();
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        List<AuthorModel> list = dataSource.getAuthors();
        list.sort(Comparator.comparing(AuthorModel::getId));
        entity.setId(list.get(list.size() - 1).getId() + 1L);
        //entity.setId(list.size() + 1L);
        entity.setCreateDate(time);
        entity.setLastUpdateDate(time);
        list.add(entity);
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        AuthorModel authorModel;
        Optional<AuthorModel> optional = readById(entity.getId());
        if (optional.isPresent()) {
            authorModel = optional.get();
            authorModel.setName(entity.getName());
            authorModel.setLastUpdateDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            return authorModel;
        } else {
            return optional.orElse(new AuthorModel());
        }
    }
    @DeleteNews
    @Override
    public boolean deleteById(Long id) {
        Optional<AuthorModel> optional = readById(id);
        if (optional.isPresent()) {
            List<AuthorModel> list = dataSource.getAuthors();
            return list.remove(optional.get());
        } else {
            return false;
        }
        /*
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i).getId(), id)) {
                list.remove(i);
                return true;
            }
        }
         */
    }

    @Override
    public boolean existById(Long id) {
        return dataSource.getAuthors().stream().anyMatch(news -> id.equals(news.getId()));
    }
}
