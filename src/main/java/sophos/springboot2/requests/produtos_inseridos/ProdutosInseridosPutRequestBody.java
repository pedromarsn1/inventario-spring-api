package sophos.springboot2.requests.produtos_inseridos;

import lombok.Data;

@Data
public class ProdutosInseridosPutRequestBody {
    private int id;
    private String nome;
    private int codProduto;
    private String unidade;
    private int quantidade;
    private String grupo;
}
