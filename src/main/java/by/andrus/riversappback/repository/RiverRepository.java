package by.andrus.riversappback.repository;

import by.andrus.riversappback.model.River;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiverRepository extends JpaRepository<River, Long> {
}
