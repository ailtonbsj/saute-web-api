package ailtonbsj.sauteweb.sauteapi.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Endereco {
    @Column(nullable = false)
    String cep;

    @Column(nullable = false)
    String rua;

    @Column
    Long numero;

    @Column(nullable = false)
    String bairro;

    @Column(nullable = false)
    String uf;

    @Column(nullable = false)
    String municipio;
}
