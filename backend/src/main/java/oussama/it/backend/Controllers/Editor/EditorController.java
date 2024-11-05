package oussama.it.backend.Controllers.Editor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Editor")
@RequiredArgsConstructor
public class EditorController {
    @GetMapping
    public ResponseEntity<String> sayhello() {
        return ResponseEntity.ok("Hello World");
    }
}
