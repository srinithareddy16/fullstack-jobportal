package com.jobportal.jobportal_backend.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

@Component
public class JwtUtil {

	@Value("${app.jwt.secret}")
	private String secret;
	
	public String generateToken(String email, String role) {
	    return Jwts.builder()
	            .setSubject(email)
	            .claim("role", role)  // ✅ Adding role here
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
	            .signWith(SignatureAlgorithm.HS256, secret)
	            .compact();
	}
	
	public String extractRole(String token) {
	    return Jwts.parser()
	            .setSigningKey(secret)
	            .parseClaimsJws(token)
	            .getBody()
	            .get("role", String.class);  // ✅ extracting the role from token
	}



    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()));
    }
}

