package by.andrus.riversappback.service.impl;

import by.andrus.riversappback.model.Status;
import by.andrus.riversappback.model.User;
import by.andrus.riversappback.repository.RoleRepository;
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
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(user.getRoles());
        user.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.save(user);
        log.info("IN UserServiceImpl.register user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        log.info("IN UserServiceImpl.register - user: {} users found", users.size());
        return users;
    }

    @Override
    public User findByUserName(String username) {
        User user = userRepository.findByUsername(username);
        log.info("IN UserServiceImpl.findByUserName - user: {} found by username: {}", user, username);
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.info("IN UserServiceImpl.findById - no user found by id: {}", id);
            return null;
        }
        log.info("IN UserServiceImpl.findById - user: {} found by id: {}", user, id);
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN UserServiceImpl.delete - user with id: {} successfully deleted", id);
    }
}
