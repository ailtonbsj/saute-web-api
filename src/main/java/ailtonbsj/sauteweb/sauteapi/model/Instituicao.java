package ailtonbsj.sauteweb.sauteapi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Instituicao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String instituicao;

    @ManyToOne(cascade = CascadeType.DETACH)
    NivelEscolar nivelEscolar;

    @Embedded
    Endereco endereco;

    String email;

    String dependencia;

    String entidade;

    String credenciamento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate validadeCredenciamento;

    String recredenciamento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate validadeRecredenciamento;
    
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime updatedAt;

    @PrePersist
    public void beforeSave() {
        LocalDateTime now = LocalDateTime.now();
        setCreatedAt(now);
        setUpdatedAt(now);
    }
}
