package oussama.it.backend.Services.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {
    // Stockez la clé de manière sécurisée
    private final String SECRET_KEY = "mnbvcxzlkjhgfdsapoiuytrewq123456789ufhyrjkdyruq1w2e3r4t5y6u7i8o9p0plokijugfyuu3453##";

    // Générez la clé une seule fois
    private Key getSiginKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())

                // Définition de la date d'émission du token
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // Définition de la date d'expiration du token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5))
                // Signature du token avec la clé générée par getSiginKey() et l'algorithme HS256
                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                //HS256 est une algorithme pour calculer la signature
                // Compilation de toutes ces informations en une chaîne JWT compacte
                .compact();
    }

    // pour extraire le username nous devions implimenter cette methode

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // Extraction de tous les claims du token
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    // Méthode pour extraire tous les claims d'un token JWT
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                // Définit la clé de signature utilisée pour vérifier le token
                .setSigningKey(getSiginKey())
                // Construit le parseur JWT
                .build()
                // Parse le token JWT et vérifie sa signature
                .parseClaimsJws(token)
                // Récupère le corps du token qui contient les claims
                .getBody();
    }

    public String extractUsername(String token) {
        // Utilise la méthode extractClaim pour obtenir le sujet (subject) du token
        return extractClaim(token, Claims::getSubject);
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        // Vérifie si le nom d'utilisateur extrait du token correspond à celui des détails de l'utilisateur
        // et si le token n'est pas expiré
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    @Override
    public String generateRefreshToken(HashMap<String, Object> extractClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extractClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 60))
                .signWith(getSiginKey(),SignatureAlgorithm.HS256)
                .compact();

    }

    private boolean isTokenExpired(String token) {
        // Extrait la date d'expiration du token JWT
        Date expirationDate = extractClaim(token, Claims::getExpiration);

        // Vérifie si la date d'expiration est antérieure à la date actuelle
        // Si oui, cela signifie que le token est expiré
        return expirationDate.before(new Date());
    }

}
