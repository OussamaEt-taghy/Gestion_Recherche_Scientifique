package oussama.it.backend.Services.AuthoreRoleServices;


import oussama.it.backend.DTOs.AuthorDtos.AuthorRequestDTO;
import oussama.it.backend.Entities.Author;

import java.util.List;

public interface AuthorServices {
    Author AddAuthor(AuthorRequestDTO authorRequest);
    Author updateAuthor(long id , AuthorRequestDTO authorRequest);
    void deleteAuthor(long id);
    List<Author> getAllAuthor();
}
