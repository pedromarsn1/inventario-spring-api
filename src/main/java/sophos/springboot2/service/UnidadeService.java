package sophos.springboot2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sophos.springboot2.domain.Unidade;
import sophos.springboot2.repository.UnidadeRepository;
import sophos.springboot2.requests.unidade.UnidadePostRequestBody;
import sophos.springboot2.requests.unidade.UnidadePutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadeService {
    private final UnidadeRepository unidadeRepository;

    public List<Unidade> listAll() {
        return unidadeRepository.findAll();
    }

    public Unidade findbyidOrThowBadRequestException(int id) {
        return unidadeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unidade e grupo não encontrados"));
    }

    public Unidade saveUniGrupo(UnidadePostRequestBody unidadePostRequestBody){
        return unidadeRepository.save(Unidade.builder()
                .idUnidade(unidadePostRequestBody.getIdUnidade())
                .nomeUnidade(unidadePostRequestBody.getNomeUnidade())
                .build());

    }

    public void delete(int id){
        unidadeRepository.delete(findbyidOrThowBadRequestException(id));
    }

    public void replaceUniGrupo(UnidadePutRequestBody unidadePutRequestBody){
        Unidade unidade = Unidade.builder()
                .idUnidade(unidadePutRequestBody.getIdUnidade())
                .nomeUnidade(unidadePutRequestBody.getNomeUnidade())
                .build();

        unidadeRepository.save(unidade);
    }
}
