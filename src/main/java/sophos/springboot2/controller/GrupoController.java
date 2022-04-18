package sophos.springboot2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sophos.springboot2.domain.Grupo;
import sophos.springboot2.domain.Produto;
import sophos.springboot2.repository.GrupoRepository;
import sophos.springboot2.requests.GrupoPostRequestBody;
import sophos.springboot2.requests.GrupoPutRequestBody;
import sophos.springboot2.requests.ProdutoPutRequestBody;
import sophos.springboot2.service.GrupoService;
import sophos.springboot2.util.DateUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("grupos")
@Log4j2
@RequiredArgsConstructor
@Component
public class GrupoController {
    @Autowired
    private final DateUtil dateUtil;
    private final GrupoRepository grupoRepository;
    private final GrupoService grupoService;

    @GetMapping
    public ResponseEntity<List<Grupo>> list(){
        log.info(dateUtil.formatLocalTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(grupoService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/id"})
    public ResponseEntity<Grupo> findById(@PathVariable int id){
        return new ResponseEntity<>(grupoService.findbyidOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Grupo> save(@RequestBody GrupoPostRequestBody grupoPostRequestBody) {
        return new ResponseEntity<>(grupoService.save(grupoPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        grupoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Grupo> Put(@PathVariable(value = "id") int id, @RequestBody GrupoPutRequestBody grupoPutRequestBody) {
        Optional<Grupo> antigoGrupo = grupoRepository.findById(id);

        if(antigoGrupo.isPresent()){
            Grupo grupo = antigoGrupo.get();
            grupo.setNomeGrupo(grupoPutRequestBody.getNomeGrupo());

            grupoRepository.save(grupo);
            return new ResponseEntity<Grupo>(grupo, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
