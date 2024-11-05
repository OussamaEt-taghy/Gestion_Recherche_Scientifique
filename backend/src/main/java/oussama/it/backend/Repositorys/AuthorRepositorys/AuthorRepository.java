package oussama.it.backend.Repositorys.AuthorRepositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import oussama.it.backend.Entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
