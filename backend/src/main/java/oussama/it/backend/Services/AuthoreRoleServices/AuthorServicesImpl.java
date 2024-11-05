package oussama.it.backend.Services.AuthoreRoleServices;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oussama.it.backend.DTOs.AuthorDtos.AuthorRequestDTO;
import oussama.it.backend.Entities.Author;
import oussama.it.backend.Entities.AuthorGroupe;
import oussama.it.backend.Repositorys.AuthorRepositorys.AuthorGroupsRepository;
import oussama.it.backend.Repositorys.AuthorRepositorys.AuthorRepository;

import java.util.List;
@RequiredArgsConstructor
@Service
public class AuthorServicesImpl implements AuthorServices{
    @Autowired
    final private AuthorRepository authorRepository;
    @Autowired
    final private AuthorGroupsRepository authorGroupsRepository;
    @Override
    public Author AddAuthor(AuthorRequestDTO authorRequest) {
        Author newAuthor = new Author();
        newAuthor.setName(authorRequest.getName());
        newAuthor.setInstitutionName(authorRequest.getInstitutionName());
        newAuthor.setInstitutionAdress(authorRequest.getInstitutionAdress());
        newAuthor.setAffiliation(authorRequest.getAffiliation());

        return authorRepository.save(newAuthor);
    }
    @Override
    public List<Author> getAllAuthor() {
          return authorRepository.findAll();
    }

    @Override
    public Author updateAuthor(long id, AuthorRequestDTO authorRequest) {
        Author existingAuthor= authorRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Auteur non trouvé avec l'ID : " + id));

        existingAuthor.setName(authorRequest.getName());
        existingAuthor.setAffiliation(authorRequest.getAffiliation());
        existingAuthor.setInstitutionName(authorRequest.getInstitutionName());
        existingAuthor.setInstitutionAdress(authorRequest.getInstitutionAdress());

        return authorRepository.save(existingAuthor);
    }


    @Override
    public void deleteAuthor(long id) {
        // Retrieve the author by ID
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with ID: " + id));

        // Remove author from each group they're a member of
        for (AuthorGroupe group : author.getGroups()) {
            group.getMembres().remove(author);
           authorGroupsRepository.save(group);  // Persist the updated group
        }

        // Clear groups list from author to break references
        author.getGroups().clear();

        // Now delete the author
        authorRepository.delete(author);
    }



}
