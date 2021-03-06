package by.andrus.riversappback.service.impl;

import by.andrus.riversappback.model.Status;
import by.andrus.riversappback.model.User;
import by.andrus.riversappback.repository.UserRepository;
import by.andrus.riversappback.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        User createdUser = userRepository.save(user);
        log.info("IN UserServiceImpl.create user: {} successfully created", createdUser);
        return createdUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        log.info("IN UserServiceImpl.getAll: {} users found", users.size());
        return users;
    }

    @Override
    public User getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        log.info("IN UserServiceImpl.getByUserName - user: {} found by username: {}", user, username);
        return user;
    }

    @Override
    public User getById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.info("IN UserServiceImpl.getById - no user found by id: {}", id);
            return null;
        }
        log.info("IN UserServiceImpl.getById - user: {} found by id: {}", user, id);
        return user;
    }

    @Override
    public User update(User changedUser) throws IllegalAccessException {
        User dbUser = getById(changedUser.getId());
        if (dbUser == null)
            throw new IllegalArgumentException("There is NO such user in DB");
        if (changedUser.getPassword() != null)
            changedUser.setPassword(passwordEncoder.encode(changedUser.getPassword()));
        changedUser.addMissing(dbUser);
        User updatedUser = userRepository.save(changedUser);
        log.info("IN UserServiceImpl.update user: {} successfully updated", updatedUser);
        return updatedUser;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
        log.info("IN UserServiceImpl.deleteById - user with id: {} successfully deleted", id);
    }
}
