package oussama.it.backend.Controllers.Author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import oussama.it.backend.DTOs.AuthorDtos.AuthorGroupRequestDTO;
import oussama.it.backend.Entities.AuthorGroupe;
import oussama.it.backend.Services.AuthoreRoleServices.AuthorGroupServices;

import java.util.List;

@RestController
@RequestMapping("/api/author-groups")
@RequiredArgsConstructor
public class AuthorGroupsController {

    private final AuthorGroupServices authorGroupServices;

    // Endpoint pour ajouter un nouveau groupe
    @PostMapping("/add")
    public ResponseEntity<AuthorGroupe> addAuthorGroup(@RequestBody AuthorGroupRequestDTO authorGroupRequest) {
        AuthorGroupe newGroup = authorGroupServices.AddAuthorGroupe(authorGroupRequest);
        return ResponseEntity.ok(newGroup);
    }

    // Endpoint pour obtenir tous les groupes de l'utilisateur connecté
    @GetMapping("/all")
    public ResponseEntity<List<AuthorGroupe>> getAllGroupsForLoggedInUser() {
        List<AuthorGroupe> groups = authorGroupServices.getAllGroupsForLoggedInUser();
        return ResponseEntity.ok(groups);
    }

    // Endpoint pour mettre à jour un groupe existant
    @PutMapping("/update/{id}")
    public ResponseEntity<AuthorGroupe> updateAuthorGroup(@PathVariable Long id, @RequestBody AuthorGroupRequestDTO authorGroupRequest) {
        AuthorGroupe updatedGroup = authorGroupServices.updateAuthorGroupe(id, authorGroupRequest);
        return ResponseEntity.ok(updatedGroup);
    }

    // Endpoint pour supprimer un groupe
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuthorGroup(@PathVariable Long id) {
        String result = authorGroupServices.deleteAuthorGroupe(id);
        return ResponseEntity.ok(result); // Retourne une réponse sans contenu (code 204)
    }
}
