package ailtonbsj.sauteweb.sauteapi.dto;

import java.util.List;

import lombok.Data;

@Data
public class RoleDTO {
    Long idUser;
    List<Long> idsRoles;
}
