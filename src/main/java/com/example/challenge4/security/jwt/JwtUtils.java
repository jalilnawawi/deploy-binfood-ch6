package com.example.challenge4.security.jwt;

import com.example.challenge4.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    public String generateToken(Authentication authentication){
        String username;
        if (authentication.getPrincipal() instanceof UserDetailsImpl userPrincipal){
            username = userPrincipal.getUsername();
        } else if (authentication.getPrincipal() instanceof OidcUser oidcUser){
            username = oidcUser.getEmail();
        } else {
            throw new IllegalArgumentException("Unsupported principal type");
        }

        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+jwtExpiration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsername(String jwt){
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey()).build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateJwtToken(String authToken) throws SignatureException {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(authToken);
            return true;
        } catch (MalformedJwtException e){
            logger.error("Invalid JWT Token: {}", e.getMessage());
        } catch (ExpiredJwtException e){
            logger.error("JWT Token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e){
            logger.error("JWT Token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}
