package sophos.springboot2.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sophos.springboot2.domain.Produto;

import javax.management.BadAttributeValueExpException;
import java.util.List;

@Service
public class ProdutoService { //responsável pela regra de negócios
// private final AnimeRepository animeRepository

 private List<Produto> produtos =
         List.of(new Produto(123, "Escova de Dente", 152558, "UN", 800, "SER Educacional"),
                 new Produto(555, "Doritos", 18566, "UN", 566, "Elma Chips"));

 public List<Produto> listAll() {
  return  produtos;
 }

 public Produto findById(int id) {
  return produtos.stream()
          .filter(produto -> produto.getId().equals(id))
          .findFirst()
          .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Produto não encontrado"));
 }
}