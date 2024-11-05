package oussama.it.backend.Controllers.Editor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oussama.it.backend.DTOs.EditorDtos.KeywordDTO;
import oussama.it.backend.Entities.Keyword;
import oussama.it.backend.Services.EditorRoleServices.KeywordServices;

@RestController
@RequestMapping("api/Editor/keyword")
@RequiredArgsConstructor
public class KeywordController {
    public final KeywordServices keywordServices;
    @PostMapping("/add")
    public ResponseEntity<Keyword> addKeyword(@RequestBody KeywordDTO keywordDTO) {
        Keyword newKeyword = keywordServices.AddKeyword(keywordDTO);
        return ResponseEntity.ok(newKeyword);
    }
}
