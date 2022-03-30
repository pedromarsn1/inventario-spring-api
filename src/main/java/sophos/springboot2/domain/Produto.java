package sophos.springboot2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //constroi todos os getters e setters da aplicação
@AllArgsConstructor
public class Produto {
    private int id;
    private String nome;
    private int codProduto;
    private String unidade;
    private int quantidade;
    private String grupo;


}
