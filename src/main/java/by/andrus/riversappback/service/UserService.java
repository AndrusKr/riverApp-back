package by.andrus.riversappback.service;

import by.andrus.riversappback.model.User;

import java.util.List;

public interface UserService {
    User create(User user);

    List<User> getAll();

    User getByUsername(String username);

    User getById(Long id);

    User update(User user) throws InstantiationException, IllegalAccessException;

    void deleteById(Long id);
}
