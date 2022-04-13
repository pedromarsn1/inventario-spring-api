package sophos.springboot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sophos.springboot2.domain.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo,Integer> {
}
