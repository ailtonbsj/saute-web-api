package ailtonbsj.sauteweb.sauteapi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Professor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    String nome;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    LocalDate nascimento;

    @Column(nullable = false)
    String naturalidade;

    @Column(nullable = false)
    String cpf;

    @Column(nullable = false)
    String rg;

    @Column
    String orgaoEmissor;

    @Embedded
    Endereco endereco;

    @Column
    String telefone;

    @Column(nullable = false)
    String celular;

    @Column(nullable = false)
    String email;

    @Column
    String pai;

    @Column
    String mae;

    @Column(nullable = false)
    String habilitacao;

    @Column
    String categoriaCNH;

    @Column(length = 100000)
    String foto;

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
