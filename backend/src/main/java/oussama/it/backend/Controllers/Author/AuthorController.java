package oussama.it.backend.Controllers.Author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oussama.it.backend.DTOs.AuthorDtos.AuthorRequestDTO;
import oussama.it.backend.Entities.Author;
import oussama.it.backend.Services.AuthoreRoleServices.AuthorServices;
import java.util.List;

@RestController
@RequestMapping("/api/Author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorServices authorServices;

    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorRequestDTO authorRequest){

        Author newAuthor = authorServices.AddAuthor(authorRequest);
        return ResponseEntity.ok(newAuthor);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> Authors = authorServices.getAllAuthor();
        return ResponseEntity.ok(Authors);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequestDTO authorRequest) {
        Author updatedAuthor = authorServices.updateAuthor(id, authorRequest);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorServices.deleteAuthor(id);
        return ResponseEntity.noContent().build(); // Retourne une réponse sans contenu (code 204)
    }

}
