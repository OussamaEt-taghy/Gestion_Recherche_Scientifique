package oussama.it.backend.Repositorys.EditorRepositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import oussama.it.backend.Entities.Keyword;
@Repository
public interface KeywordRepository extends JpaRepository<Keyword,Long> {
}
