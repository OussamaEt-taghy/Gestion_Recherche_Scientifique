package oussama.it.backend.Services.Auth;

import oussama.it.backend.DTOs.JwtAuthenticationResponse;
import oussama.it.backend.DTOs.RefreshTokenRequest;
import oussama.it.backend.DTOs.SignInRequest;
import oussama.it.backend.DTOs.SignUpRequest;
import oussama.it.backend.Entities.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SignInRequest signInRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
