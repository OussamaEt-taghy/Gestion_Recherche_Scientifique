package oussama.it.backend.Controllers.Author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import oussama.it.backend.DTOs.AuthorDtos.ArticleDTO;
import oussama.it.backend.DTOs.AuthorDtos.AuthorRequestDTO;
import oussama.it.backend.Entities.Article;
import oussama.it.backend.Entities.Author;
import oussama.it.backend.Services.AuthoreRoleServices.ArticleServices;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api/Author")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleServices articleServices;

    @PostMapping("/article/add")
    public ResponseEntity<Article> addArticle(
            @RequestParam("file") MultipartFile file,
            @RequestParam("ArticleSummary") String articleSummary,
            @RequestParam("KeywordIds") List<Long> keywordIds
    ) throws IOException {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setFile(file);
        articleDTO.setArticleSummary(articleSummary);
        articleDTO.setKeywordIds(keywordIds);

        Article newArticle = articleServices.AddArticle(articleDTO);
        return ResponseEntity.ok(newArticle);
    }

}

