package com.himanshu.Spring_Security_Practice01.helper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {
    //@Value("${jwt.secret}")
    private String jwtSecret = "K9#mL2$pQ7@nR4%tW8^yU6&hV3!xZ5*j"; // Secret key for signing JWTs

    //@Value("${jwt.expiration}")
    private int jwtExpirationMs = 86400000; // JWT expiration time in milliseconds (e.g., 1 day)

    public String getJwtFromHeader(String header) {
        // Logic to extract JWT from the Authorization header
        return null;
    }

    public String generateJWTTokenFromUserName(String userName) {
        // Logic to generate JWT token using the username
        return Jwts.builder()
                .setSubject(userName)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    public boolean validateJWTToken(String token) {
        // Logic to validate the JWT token
        // This is a placeholder implementation
        return token != null && token.startsWith("generated-jwt-token-for-");
    }

    private Key key() {
        // Logic to return the signing key
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
