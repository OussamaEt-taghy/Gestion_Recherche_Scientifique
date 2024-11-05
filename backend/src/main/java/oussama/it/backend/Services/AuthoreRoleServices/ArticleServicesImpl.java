package oussama.it.backend.Services.AuthoreRoleServices;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import oussama.it.backend.DTOs.AuthorDtos.ArticleDTO;
import oussama.it.backend.Entities.Article;
import oussama.it.backend.Entities.Corresponding;
import oussama.it.backend.Entities.ENUMs.SubmissionStatus;
import oussama.it.backend.Entities.Keyword;
import oussama.it.backend.Entities.Submission;
import oussama.it.backend.Repositorys.AuthorRepositorys.ArticleRepository;
import oussama.it.backend.Repositorys.EditorRepositorys.KeywordRepository;
import oussama.it.backend.Repositorys.UserRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleServicesImpl implements ArticleServices{
    @Autowired
 private UserRepository userRepository;
    @Autowired
 private KeywordRepository keywordRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article AddArticle(ArticleDTO articleDTO) throws IOException {
        // Obtenir l'auteur correspondant connecté
        Corresponding correspondingAuthor = getLoggedInCorresponding();

        // Créer un nouvel article
        Article article = new Article();

        // Définir le contenu de l'article à partir du fichier
        article.setContentFromMultipartFile(articleDTO.getFile());

        // Utiliser le résumé fourni par l'auteur
        article.setArticleSummary(articleDTO.getArticleSummary());

        // Associer les mots-clés
        List<Keyword> keywords = keywordRepository.findAllById(articleDTO.getKeywordIds());
        for (Keyword keyword : keywords) {
            keyword.setArticle(article); // Associer chaque mot-clé à l'article
        }
        article.setKeywords(keywords);  // Définir la liste des mots-clés pour l'article

        // Créer la soumission
        Submission submission = new Submission();
        submission.setArticle(article);
        submission.setCorresponding(correspondingAuthor);
        submission.setSubmissionDate(new Date());
        submission.setStatus(SubmissionStatus.Inprogress);
        article.setSubmission(submission); // Associer la soumission à l'article

        // Enregistrer l'article avec la soumission et les mots-clés
        return articleRepository.save(article);
    }


    @Override
    public Corresponding getLoggedInCorresponding() {
        // Récupérer l'email de l'utilisateur connecté à partir du contexte de sécurité
        String correspondingEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(correspondingEmail);
        return userRepository.findByEmail(correspondingEmail)
                .filter(user -> user instanceof Corresponding) // Filtrer pour s'assurer que c'est bien un Corresponding
                .map(user -> (Corresponding) user) // Convertir en `Corresponding`
                .orElseThrow(() -> new IllegalArgumentException("Aucun auteur correspondant trouvé pour cet utilisateur"));
    }
}
