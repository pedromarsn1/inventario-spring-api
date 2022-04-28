package sophos.springboot2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sophos.springboot2.domain.Produto;
import sophos.springboot2.repository.ProdutoRepository;
import sophos.springboot2.requests.produto.ProdutoPostRequestBody;
import sophos.springboot2.requests.produto.ProdutoPutRequestBody;
import sophos.springboot2.service.ProdutoService;
import sophos.springboot2.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController // faz com que o retorno dos dados sejam apenas strings
@RequestMapping("produtos") // rota no nível da classe
@Log4j2
@RequiredArgsConstructor
public class ProdutoController {
    @Autowired
    private final DateUtil dateUtil;
    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> list() {
        log.info(dateUtil.formatLocalTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(produtoService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable int id) {
        return new ResponseEntity<>(produtoService.findbyidOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody ProdutoPostRequestBody produtoPostRequestBody) {
        return new ResponseEntity<>(produtoService.save(produtoPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        produtoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Produto> Put(@PathVariable(value = "id") int id, @RequestBody ProdutoPutRequestBody produtoPutRequestBody) {
        Optional<Produto> antigoProduto = produtoRepository.findById(id);
        if (antigoProduto.isPresent()) {
            Produto produto = antigoProduto.get();
            produto.setNome(produtoPutRequestBody.getNome());
            produto.setCodProduto(produtoPutRequestBody.getCodProduto());
            produto.setUnidade(produtoPutRequestBody.getUnidade());
            produto.setGrupo(produtoPutRequestBody.getGrupo());
            produto.setQuantidade(produtoPutRequestBody.getQuantidade());
            produtoRepository.save(produto);
            return new ResponseEntity<Produto>(produto, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
