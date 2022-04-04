package sophos.springboot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sophos.springboot2.domain.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> { //vai conectar ao banco

}
