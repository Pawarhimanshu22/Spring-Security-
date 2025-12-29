package com.himanshu.JWT.utility;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil
{
    private static final String SECRET_KEY_STRING = "Himanshupawar2293HimanshuKalbhor0`293"; // Must be at least 256 bits for HS256
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

    public String GENERATE_TOKEN(String username)
    {
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ 10 * 60 * 1000)) // 10 minutes
                .signWith(SECRET_KEY)
                .compact();
        System.out.println("Generated Token: " + token);
        return token;
    }

    public boolean VALIDATE_TOKEN(String token)
    {
        try
        {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }
        catch (ExpiredJwtException e)
        {
            System.out.println("Token validation failed: " + e.getMessage());
            return false;
        }
        catch (SignatureException e)
        {
            System.out.println("Token validation failed: " + e.getMessage());
            return false;
        }
        catch (Exception e)
        {
            System.out.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }

}
