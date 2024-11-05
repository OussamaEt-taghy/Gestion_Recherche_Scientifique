package oussama.it.backend.DTOs.AuthorDtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ArticleDTO {
    private MultipartFile file;
    private String ArticleSummary;
    private List<Long> KeywordIds;
}
