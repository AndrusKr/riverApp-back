package by.andrus.riversappback.repository;

import by.andrus.riversappback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    void deleteById(Long id);
}
