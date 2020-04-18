package by.andrus.riversappback.service.impl;

import by.andrus.riversappback.model.Role;
import by.andrus.riversappback.repository.RoleRepository;
import by.andrus.riversappback.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Cacheable("roles")
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getByName(String name) {
        return getAll().stream()
                .filter(role -> role.getName().equals(name))
                .findAny().orElse(null);
    }

    @Override
    @CacheEvict(value = "roles", key = "id")
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    public boolean hasName(String name) {
        return getAllNames().contains(name);
    }

    public List<String> getAllNames() {
        return getAll().stream().map(Role::getName).collect(Collectors.toList());
    }
}
