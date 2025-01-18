package com.syndicate.jwt;

import com.syndicate.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class JwtGenerator {

    public String generateToken(Authentication authentication, String userType) {
        String username= authentication.getName();
        Date currentDate = Date.valueOf(LocalDate.now());
        Date expiryDate = new Date(currentDate.getTime()+ SecurityConstants.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.JJWT_SECRET)
                .claim("usertype", userType)
                .compact();
        return token;
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JJWT_SECRET)
                .build().parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String getUserTypeFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JJWT_SECRET)
                .build().parseClaimsJws(token)
                .getBody();
        return claims.get("usertype").toString();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.JJWT_SECRET).build().parseClaimsJws(token);
            return true;
        }
        catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT token is not valid " + token);
        }
    }
}
