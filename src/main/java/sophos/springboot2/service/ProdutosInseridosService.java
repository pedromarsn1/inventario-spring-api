package sophos.springboot2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sophos.springboot2.domain.ProdutosInseridos;
import sophos.springboot2.repository.ProdutosInseridosRepository;
import sophos.springboot2.requests.produtos_inseridos.ProdutosInseridosPostRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutosInseridosService {
    private final ProdutosInseridosRepository produtosInseridosRepository;

    public List<ProdutosInseridos> listAll() {
        return produtosInseridosRepository.findAll();
    }

    public ProdutosInseridos findbyidOrThrowBadRequestException(int id) {
        return produtosInseridosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));

    }

    public ProdutosInseridos save(ProdutosInseridosPostRequestBody produtosInseridosPostRequestBody) {
        //produto.setId(ThreadLocalRandom.current().nextInt(3,1000000)); //se eu quiser colocar um id aleatório

        return produtosInseridosRepository.save(ProdutosInseridos.builder()
                .id(produtosInseridosPostRequestBody.getId())
                .nome(produtosInseridosPostRequestBody.getNome())
                .grupo(produtosInseridosPostRequestBody.getGrupo())
                .unidade(produtosInseridosPostRequestBody.getUnidade())
                .codProduto(produtosInseridosPostRequestBody.getCodProduto())
                .quantidade(produtosInseridosPostRequestBody.getQuantidade())
                .build());


    }

    public void delete(int id) {
        produtosInseridosRepository.delete(findbyidOrThrowBadRequestException(id));
    }


}
