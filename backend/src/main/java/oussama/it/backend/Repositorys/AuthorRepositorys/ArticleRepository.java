package oussama.it.backend.Repositorys.AuthorRepositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import oussama.it.backend.Entities.Article;
@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
}
