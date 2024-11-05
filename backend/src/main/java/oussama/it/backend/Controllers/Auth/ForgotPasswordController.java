package oussama.it.backend.Controllers.Auth;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import oussama.it.backend.DTOs.ChangePassword;
import oussama.it.backend.DTOs.MailBody;
import oussama.it.backend.Entities.User;
import oussama.it.backend.Entities.forgotPassword;
import oussama.it.backend.Repositorys.ForgotPasswordRepository;
import oussama.it.backend.Repositorys.UserRepository;
import oussama.it.backend.Services.forgotPassword.EmailService;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ForgotPasswordController(UserRepository userRepository, EmailService emailService, ForgotPasswordRepository forgotPasswordRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyMail(@PathVariable String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Veuillez fournir une adresse e-mail valide"));

        long otp = generateOTP();
        System.out.println(otp);

        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("Voici le code OTP pour votre demande de réinitialisation de mot de passe : " + otp)
                .subject("Code OTP pour la demande de réinitialisation de mot de passe")
                .build();

        forgotPassword fp = forgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .user(user)
                .build();

        emailService.sendSimpleMail(mailBody);
        forgotPasswordRepository.save(fp);

        return ResponseEntity.ok("E-mail envoyé pour vérification");
    }

    @PostMapping("/verifyotp/{otp}/{email}")
    public ResponseEntity<String> verifyOtp(@PathVariable Long otp, @PathVariable String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Veuillez fournir une adresse e-mail valide"));

        forgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user)
                .orElseThrow(() -> new RuntimeException("Code OTP invalide pour l'adresse e-mail " + email));

        if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.deleteById(fp.getId());
            return new ResponseEntity<>("Le code OTP a expiré !", HttpStatus.EXPECTATION_FAILED);
        }

        return ResponseEntity.ok("Le code OTP a été vérifié");
    }

    @PostMapping("/changePassword/{email}")
    public ResponseEntity<String> changePassword(@RequestBody ChangePassword changePassword,
                                                 @PathVariable String email) {
        if (!Objects.equals(changePassword.password(), changePassword.RepeatPassword())) {
            return new ResponseEntity<>("Les mots de passe ne correspondent pas", HttpStatus.BAD_REQUEST);
        }


        String encodedPassword = passwordEncoder.encode(changePassword.password());
        userRepository.updatePassword(email, encodedPassword);
        return ResponseEntity.ok("Le mot de passe a été changé !");
    }

    private int generateOTP() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
}
