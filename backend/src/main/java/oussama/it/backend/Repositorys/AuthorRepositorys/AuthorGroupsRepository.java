package oussama.it.backend.Repositorys.AuthorRepositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import oussama.it.backend.Entities.AuthorGroupe;
import oussama.it.backend.Entities.Corresponding;

import java.util.List;

@Repository
public interface AuthorGroupsRepository extends JpaRepository<AuthorGroupe,Long> {

    List<AuthorGroupe> findByCorrespondingAuthor(Corresponding correspondingAuthor);

}
