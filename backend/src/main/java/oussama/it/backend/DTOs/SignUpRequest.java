package oussama.it.backend.DTOs;
import lombok.Data;
import oussama.it.backend.Entities.ENUMs.Roles;

@Data
public class SignUpRequest {
    private String fullname;
    private String email;
    private String password;
    private Roles role;

}
