package ailtonbsj.sauteweb.sauteapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleToken {
    String email;
    String name;
    String picture;
    String iat;
    String exp;
}
