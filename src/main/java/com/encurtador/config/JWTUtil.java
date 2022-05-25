package com.encurtador.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

private String secret = "My007TopSecureJWTSign";
	
	private Long expiration = (long) 300000;
	
	public String generateToken(String login) {
		return Jwts.builder()
					.setSubject(login)
					.setExpiration(new Date(System.currentTimeMillis() + this.expiration))
					.signWith(SignatureAlgorithm.HS512, secret.getBytes())
					.compact();
	}
	
	public boolean isTokenValid(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if(username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}
	
	public String getSubject(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}catch(Exception e) {
			return null;
		}
		
	}
}
