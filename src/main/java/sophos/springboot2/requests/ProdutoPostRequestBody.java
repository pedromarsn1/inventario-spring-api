package sophos.springboot2.requests;

import lombok.Data;

@Data
public class ProdutoPostRequestBody {
    private int id;
    private String nome;
    private int codProduto;
    private String unidade;
    private int quantidade;
    private String grupo;
}
