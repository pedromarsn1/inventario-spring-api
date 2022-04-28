package sophos.springboot2.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sophos.springboot2.domain.Grupo;
import sophos.springboot2.repository.GrupoRepository;
import sophos.springboot2.requests.grupo.GrupoPostRequestBody;
import sophos.springboot2.requests.grupo.GrupoPutRequestBody;

import java.util.List;

@Service
public class GrupoService {
   private final GrupoRepository grupoRepository;

    public GrupoService(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    public List<Grupo> listAll() {
        return grupoRepository.findAll();
    }

    public Grupo findbyidOrThrowBadRequestException(int id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));

    }

    public Grupo save(GrupoPostRequestBody grupoPostRequestBody) {
        //produto.setId(ThreadLocalRandom.current().nextInt(3,1000000)); //se eu quiser colocar um id aleatório

        return grupoRepository.save(Grupo.builder()
                .idGrupo(grupoPostRequestBody.getIdGrupo())
                .nomeGrupo(grupoPostRequestBody.getNomeGrupo())
                .build());
    }

    public void delete(int id) {
        grupoRepository.delete(findbyidOrThrowBadRequestException(id));
    }


    public void replaceGrupo(GrupoPutRequestBody grupoPutRequestBody) {
        Grupo grupo = Grupo.builder()
                .nomeGrupo(grupoPutRequestBody.getNomeGrupo())
                .build();

        grupoRepository.save(grupo);
    }
}
