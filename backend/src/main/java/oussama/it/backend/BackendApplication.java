package oussama.it.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import oussama.it.backend.Entities.ENUMs.Roles;
import oussama.it.backend.Entities.Editor;
import oussama.it.backend.Entities.User;
import oussama.it.backend.Repositorys.UserRepository;

@SpringBootApplication
@RequiredArgsConstructor
public class BackendApplication implements CommandLineRunner {
   @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Vérifiez si un éditeur existe déjà
        User editor = userRepository.findByRole(Roles.Editor);

        if (editor == null) {
            Editor newEditor = new Editor();

            newEditor.setFullname("Oussama Et-taghy");
            newEditor.setEmail("oussamaettaghy.it@gmail.com");
            newEditor.setRole(Roles.Editor);
            newEditor.setPassword(new BCryptPasswordEncoder().encode("Eghy@@2002"));
            newEditor.setMagazineName("FSTS Magazine");

            userRepository.save(newEditor);
        }
    }
}
