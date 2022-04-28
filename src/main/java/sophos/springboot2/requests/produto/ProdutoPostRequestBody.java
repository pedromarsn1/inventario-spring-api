package sophos.springboot2.requests.produto;

import lombok.Data;

@Data
public class ProdutoPostRequestBody {
    private int id;
    private String nome;
    private String grupo;
    private String unidade;
    private int codProduto;
    private int quantidade;

}
