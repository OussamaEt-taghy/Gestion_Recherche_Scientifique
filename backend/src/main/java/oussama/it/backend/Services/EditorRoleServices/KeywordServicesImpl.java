package oussama.it.backend.Services.EditorRoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oussama.it.backend.DTOs.EditorDtos.KeywordDTO;
import oussama.it.backend.Entities.Keyword;
import oussama.it.backend.Repositorys.EditorRepositorys.KeywordRepository;

@Service
public class KeywordServicesImpl implements KeywordServices{
    @Autowired
    private KeywordRepository keywordRepository;

    @Override
    public Keyword AddKeyword(KeywordDTO keywordDTO) {
        Keyword newKeyword = new Keyword();
        newKeyword.setDescription(keywordDTO.getDescription());
        return keywordRepository.save(newKeyword);
    }
}
