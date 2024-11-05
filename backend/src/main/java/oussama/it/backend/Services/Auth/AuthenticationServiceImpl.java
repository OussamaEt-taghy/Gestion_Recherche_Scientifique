package oussama.it.backend.Services.Auth;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import oussama.it.backend.DTOs.JwtAuthenticationResponse;
import oussama.it.backend.DTOs.RefreshTokenRequest;
import oussama.it.backend.DTOs.SignInRequest;
import oussama.it.backend.DTOs.SignUpRequest;
import oussama.it.backend.Entities.*;
import oussama.it.backend.Entities.ENUMs.Roles;
import oussama.it.backend.Repositorys.UserRepository;
import oussama.it.backend.Services.JWT.JWTService;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JWTService jwtService;

    @Override
    public User signup(SignUpRequest signUpRequest){
        User user = new Corresponding();
        user.setFullname(signUpRequest.getFullname());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setRole(Roles.Author);
        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signin(SignInRequest signInRequest){
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
            signInRequest.getPassword()));
        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);

        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        jwtAuthenticationResponse.setFullname(user.getFullname());
        jwtAuthenticationResponse.setRole(user.getRole().toString());

        return jwtAuthenticationResponse;

    }
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();

        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);

            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }
return null;
    }
}
