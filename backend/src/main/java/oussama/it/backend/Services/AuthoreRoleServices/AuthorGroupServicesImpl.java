package oussama.it.backend.Services.AuthoreRoleServices;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import oussama.it.backend.DTOs.AuthorDtos.AuthorGroupRequestDTO;
import oussama.it.backend.Entities.Author;
import oussama.it.backend.Entities.AuthorGroupe;
import oussama.it.backend.Entities.Corresponding;
import oussama.it.backend.Repositorys.AuthorRepositorys.AuthorGroupsRepository;
import oussama.it.backend.Repositorys.AuthorRepositorys.AuthorRepository;
import oussama.it.backend.Repositorys.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorGroupServicesImpl implements AuthorGroupServices {
    @Autowired
    private AuthorGroupsRepository authorGroupsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorRepository authorRepository;



    @Override
    public AuthorGroupe AddAuthorGroupe(AuthorGroupRequestDTO authorGrouprequest) {

        Corresponding correspondingAuthor = getLoggedInCorresponding();
        List<Author> members = authorRepository.findAllById(authorGrouprequest.getMembresIds());

        AuthorGroupe newGroup = new AuthorGroupe();

        newGroup.setGroupename(authorGrouprequest.getGroupename());
        newGroup.setCorrespondingAuthor(correspondingAuthor);
        newGroup.setMembres(members);

        return authorGroupsRepository.save(newGroup);
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

    @Override
    @Transactional
    public List<AuthorGroupe> getAllGroupsForLoggedInUser() {
        Corresponding correspondingAuthor = getLoggedInCorresponding();

        if (correspondingAuthor == null) {
            throw new IllegalArgumentException("Utilisateur non trouvé ou non authentifié.");
        }

        return authorGroupsRepository.findByCorrespondingAuthor(correspondingAuthor).stream()
                .map(group -> {
                    AuthorGroupe groupe = new AuthorGroupe();
                    groupe.setId(group.getId());
                    groupe.setGroupename(group.getGroupename());
                    groupe.setCorrespondingAuthor(group.getCorrespondingAuthor());
                    // Important: Copy the members list
                    groupe.setMembres(new ArrayList<>(group.getMembres()));
                    return groupe;
                })
                .collect(Collectors.toList());
    }
    @Override
    public AuthorGroupe updateAuthorGroupe(Long groupId, AuthorGroupRequestDTO authorGroupRequest) {
        AuthorGroupe existingGroup = authorGroupsRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Groupe d'auteurs non trouvé avec l'ID : " + groupId));

        existingGroup.setGroupename(authorGroupRequest.getGroupename());

        List<Author> members = authorRepository.findAllById(authorGroupRequest.getMembresIds());
        existingGroup.setMembres(members);

        return authorGroupsRepository.save(existingGroup);
    }


    @Override
    @Transactional
    public String deleteAuthorGroupe(Long groupId) {
        AuthorGroupe groupToDelete = authorGroupsRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Groupe d'auteurs non trouvé avec l'ID : " + groupId));

        // Supprime les références du groupe dans chaque membre (Author)
        for (Author author : groupToDelete.getMembres()) {
            author.getGroups().remove(groupToDelete);
        }

        // Efface la liste des membres du groupe
        groupToDelete.getMembres().clear();

        // Maintenant, supprimez le groupe en toute sécurité
        authorGroupsRepository.delete(groupToDelete);
        return null;
    }




}

