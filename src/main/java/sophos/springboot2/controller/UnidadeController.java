package sophos.springboot2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sophos.springboot2.domain.Unidade;
import sophos.springboot2.repository.UnidadeRepository;
import sophos.springboot2.requests.UnidadePostRequestBody;
import sophos.springboot2.requests.UnidadePutRequestBody;
import sophos.springboot2.service.UnidadeService;
import sophos.springboot2.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("unidades")
@Log4j2
@RequiredArgsConstructor
public class UnidadeController {
    @Autowired
    private final DateUtil dateUtil;
    private final UnidadeRepository unidadeRepository;
    private final UnidadeService unidadeService;

    @GetMapping
    public ResponseEntity<List<Unidade>> list(){
        log.info(dateUtil.formatLocalTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(unidadeService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/id"})
    public ResponseEntity<Unidade> findById(@PathVariable int id){
        return new ResponseEntity<>(unidadeService.findbyidOrThowBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Unidade> save(@RequestBody UnidadePostRequestBody unidadePostRequestBody) {
        return new ResponseEntity<>(unidadeService.saveUniGrupo(unidadePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        unidadeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Unidade> Put(@PathVariable(value = "id") int id, @RequestBody UnidadePutRequestBody unidadePutRequestBody) {
        Optional<Unidade> antigaUnidade = unidadeRepository.findById(id);

        if(antigaUnidade.isPresent()){
            Unidade unidade = antigaUnidade.get();
            unidade.setNomeUnidade(unidadePutRequestBody.getNomeUnidade());

            unidadeRepository.save(unidade);
            return new ResponseEntity<Unidade>(unidade,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
