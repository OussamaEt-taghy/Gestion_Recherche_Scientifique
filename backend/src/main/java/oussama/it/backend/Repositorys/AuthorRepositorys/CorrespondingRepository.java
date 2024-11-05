package oussama.it.backend.Repositorys.AuthorRepositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import oussama.it.backend.Entities.Corresponding;

@Repository
public interface CorrespondingRepository extends JpaRepository<Corresponding, Long> {
}
