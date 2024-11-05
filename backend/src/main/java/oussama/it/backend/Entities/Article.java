package oussama.it.backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import oussama.it.backend.Entities.ENUMs.Types;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Types type;
    private String ArticleSummary;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Keyword> keywords;

    private int wordCount;
    private String content;
    @OneToOne(mappedBy = "article")
    private Review reviews;
    private Date evaluationStartdate;
    @OneToOne(mappedBy = "article")
    private Submission submission;
    // Définir le contenu et les informations de l'article à partir d'un fichier multipart
    public void setContentFromMultipartFile(MultipartFile file) throws IOException {
        this.content = new BufferedReader(new InputStreamReader(file.getInputStream()))
                .lines()
                .collect(Collectors.joining(" "));
        this.wordCount = countWords(this.content);
        this.type = this.wordCount < 4000 ? Types.Court : Types.Long;
    }
    private int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        return text.trim().split("\\s+").length;
    }

}
