package oussama.it.backend.Services.EditorRoleServices;

import oussama.it.backend.DTOs.EditorDtos.KeywordDTO;
import oussama.it.backend.Entities.Keyword;

public interface KeywordServices {
    Keyword AddKeyword(KeywordDTO keywordDTO);
}
