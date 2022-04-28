package sophos.springboot2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sophos.springboot2.domain.ProdutosInseridos;
import sophos.springboot2.repository.ProdutosInseridosRepository;
import sophos.springboot2.requests.produtos_inseridos.ProdutosInseridosPostRequestBody;
import sophos.springboot2.requests.produtos_inseridos.ProdutosInseridosPutRequestBody;
import sophos.springboot2.service.ProdutosInseridosService;
import sophos.springboot2.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("produtos-inseridos")
@Log4j2
@RequiredArgsConstructor
public class ProdutosInseridosController {
    @Autowired
    private final DateUtil dateUtil;
    private final ProdutosInseridosRepository produtosInseridosRepository;
    private final ProdutosInseridosService produtosInseridosService;

    @GetMapping
    public ResponseEntity<List<ProdutosInseridos>> list() {
        log.info(dateUtil.formatLocalTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(produtosInseridosService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProdutosInseridos> findById(@PathVariable int id) {
        return new ResponseEntity<>(produtosInseridosService.findbyidOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutosInseridos> save(@RequestBody ProdutosInseridosPostRequestBody produtosInseridosPostRequestBody) {
        return new ResponseEntity<>(produtosInseridosService.save(produtosInseridosPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        produtosInseridosService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProdutosInseridos> Put(@PathVariable(value = "id") int id, @RequestBody ProdutosInseridosPutRequestBody produtosInseridosPutRequestBody) {
        Optional<ProdutosInseridos> antigoProdutosInseridos = produtosInseridosRepository.findById(id);
        if (antigoProdutosInseridos.isPresent()) {
            ProdutosInseridos produto = antigoProdutosInseridos.get();
            produto.setNome(produtosInseridosPutRequestBody.getNome());
            produto.setCodProduto(produtosInseridosPutRequestBody.getCodProduto());
            produto.setUnidade(produtosInseridosPutRequestBody.getUnidade());
            produto.setGrupo(produtosInseridosPutRequestBody.getGrupo());
            produto.setQuantidade(produtosInseridosPutRequestBody.getQuantidade());
            produtosInseridosRepository.save(produto);
            return new ResponseEntity<ProdutosInseridos>(produto, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
