package sophos.springboot2.repository;

import sophos.springboot2.domain.Produto;

import java.util.List;

public interface ProdutoRepository { //vai conectar ao banco
    List<Produto> listAll();
}
