package oussama.it.backend.Controllers.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oussama.it.backend.DTOs.JwtAuthenticationResponse;
import oussama.it.backend.DTOs.RefreshTokenRequest;
import oussama.it.backend.DTOs.SignInRequest;
import oussama.it.backend.DTOs.SignUpRequest;
import oussama.it.backend.Entities.User;
import oussama.it.backend.Services.Auth.AuthenticationService;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) {
        try {
            return ResponseEntity.ok(authenticationService.signin(signInRequest));
        } catch (Exception e) {
            e.printStackTrace(); // ou utilisez un logger
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentication failed: " + e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
}
}
