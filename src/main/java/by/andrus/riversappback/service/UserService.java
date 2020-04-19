package by.andrus.riversappback.service;

import by.andrus.riversappback.model.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void deleteById(Long id);
}
