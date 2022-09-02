package ailtonbsj.sauteweb.sauteapi.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Autorizacao extends EntityBase {
    String numero;

    @ManyToOne(optional = false, cascade = CascadeType.DETACH)
    Professor professor;

    @ManyToOne(optional = false, cascade = CascadeType.DETACH)
    Processo processo;

    @Column(nullable = false)
    String referendum;
}
