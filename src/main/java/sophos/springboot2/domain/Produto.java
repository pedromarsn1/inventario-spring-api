package sophos.springboot2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data //constroi todos os getters e setters da aplicação
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private int codProduto;
    private String unidade;
    private int quantidade;
    private String grupo;

//    {
//        "id" : "123",
//            "nome" : "Creme Seda",
//            "codProduto" : "23444"
//        "unidade" :  "Un",
//            "quantidade" : "122",
//            "grupo" :  "SEDA"
//    }
}
