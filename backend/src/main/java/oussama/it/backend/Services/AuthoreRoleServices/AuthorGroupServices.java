package oussama.it.backend.Services.AuthoreRoleServices;



import oussama.it.backend.DTOs.AuthorDtos.AuthorGroupRequestDTO;
import oussama.it.backend.Entities.AuthorGroupe;
import oussama.it.backend.Entities.Corresponding;

import java.util.List;

public interface AuthorGroupServices {
    AuthorGroupe AddAuthorGroupe(AuthorGroupRequestDTO authorGrouprequest);
    Corresponding getLoggedInCorresponding();
    List<AuthorGroupe> getAllGroupsForLoggedInUser();
    AuthorGroupe updateAuthorGroupe(Long groupId, AuthorGroupRequestDTO authorGroupRequest);
    String deleteAuthorGroupe(Long groupId);
}
