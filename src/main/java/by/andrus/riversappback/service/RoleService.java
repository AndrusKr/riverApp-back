package by.andrus.riversappback.service;

import by.andrus.riversappback.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();

    Role getByName(String name);

    void delete(Long id);
}
