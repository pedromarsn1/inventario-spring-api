package sophos.springboot2.requests.produtos_inseridos;

import lombok.Data;

@Data
public class ProdutosInseridosPostRequestBody {
    private int id;
    private String nome;
    private String grupo;
    private String unidade;
    private int codProduto;
    private int quantidade;
}
