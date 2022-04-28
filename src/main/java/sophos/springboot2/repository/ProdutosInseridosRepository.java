package sophos.springboot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sophos.springboot2.domain.ProdutosInseridos;


public interface ProdutosInseridosRepository extends JpaRepository<ProdutosInseridos,Integer> {
}
