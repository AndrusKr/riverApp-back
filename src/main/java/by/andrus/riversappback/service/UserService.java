package by.andrus.riversappback.service;

import by.andrus.riversappback.model.User;

import java.util.List;

public interface UserService {
    User create(User user);

    List<User> getAll();

    User getByUsername(String username);

    User getById(Long id);

    User findById(Long id);

    void deleteById(Long id);
}
