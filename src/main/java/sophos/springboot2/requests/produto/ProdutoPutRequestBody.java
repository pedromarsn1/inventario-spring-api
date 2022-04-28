package sophos.springboot2.requests.produto;

import lombok.Data;

@Data
public class ProdutoPutRequestBody {
    private int id;
    private String nome;
    private int codProduto;
    private String unidade;
    private int quantidade;
    private String grupo;
}
