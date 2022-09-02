package ailtonbsj.sauteweb.sauteapi.dto;

import java.time.LocalDateTime;

import ailtonbsj.sauteweb.sauteapi.entities.NivelEscolar;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NivelEscolarDTO {
    private Long id;
    private String nivelEscolar;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public NivelEscolarDTO() {
    }

    public NivelEscolarDTO(NivelEscolar nivelEscolar) {
        this.id = nivelEscolar.getId();
        this.nivelEscolar = nivelEscolar.getNivelEscolar();
        this.createdAt = nivelEscolar.getCreatedAt();
        this.updatedAt = nivelEscolar.getUpdatedAt();
    }
}
