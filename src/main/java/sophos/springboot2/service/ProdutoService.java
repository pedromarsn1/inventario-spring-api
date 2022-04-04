package sophos.springboot2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sophos.springboot2.domain.Produto;
import sophos.springboot2.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService { //responsável pela regra de negócios
 private final ProdutoRepository produtoRepository;

 public List<Produto> listAll() {
  return  produtoRepository.findAll();
 }

 public Produto findById(int id) {
  return produtoRepository.findById(id)
          .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Produto não encontrado"));

 }

 public Produto save(Produto produto) {
  //produto.setId(ThreadLocalRandom.current().nextInt(3,1000000)); //se eu quiser colocar um id aleatório
  produtos.add(produto);
  return produto;
 }

 public void delete(int id) {
  produtos.remove(findById(id));
 }


 public void replace(Produto produto) {
   delete(produto.getId());
   produtos.add(produto);
 }
}