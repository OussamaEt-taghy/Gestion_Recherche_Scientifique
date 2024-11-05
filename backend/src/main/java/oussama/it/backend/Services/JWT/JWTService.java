package oussama.it.backend.Services.JWT;

import org.springframework.security.core.userdetails.UserDetails;
import oussama.it.backend.Entities.User;

import java.util.HashMap;

public interface JWTService {
    String generateToken(UserDetails userDetails);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);


    String generateRefreshToken(HashMap<String, Object> extractClaims, UserDetails userDetails);
}
