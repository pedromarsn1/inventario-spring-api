package sophos.springboot2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sophos.springboot2.domain.Produto;
import sophos.springboot2.repository.ProdutoRepository;
import sophos.springboot2.requests.produto.ProdutoPostRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService { //responsável pela regra de negócios
    private final ProdutoRepository produtoRepository;

    public List<Produto> listAll() {
        return produtoRepository.findAll();
    }

    public Produto findbyidOrThrowBadRequestException(int id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));

    }

    public Produto save(ProdutoPostRequestBody produtoPostRequestBody) {
        //produto.setId(ThreadLocalRandom.current().nextInt(3,1000000)); //se eu quiser colocar um id aleatório

        return produtoRepository.save(Produto.builder()
                .id(produtoPostRequestBody.getId())
                .nome(produtoPostRequestBody.getNome())
                .grupo(produtoPostRequestBody.getGrupo())
                .unidade(produtoPostRequestBody.getUnidade())
                .codProduto(produtoPostRequestBody.getCodProduto())
                .quantidade(produtoPostRequestBody.getQuantidade())
                .build());

    }

    public void delete(int id) {
        produtoRepository.delete(findbyidOrThrowBadRequestException(id));
    }



}