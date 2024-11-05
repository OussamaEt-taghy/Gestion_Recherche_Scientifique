package oussama.it.backend.Services.AuthoreRoleServices;

import oussama.it.backend.DTOs.AuthorDtos.ArticleDTO;
import oussama.it.backend.Entities.Article;
import oussama.it.backend.Entities.Corresponding;

import java.io.IOException;

public interface ArticleServices {
    Article AddArticle(ArticleDTO articleDTO) throws IOException;
     Corresponding getLoggedInCorresponding();
}
