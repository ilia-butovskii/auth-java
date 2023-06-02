package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.enteties.Admin;

public interface ProjectRepository extends JpaRepository<Admin, Long> {
    boolean existsByName(String name);
}
