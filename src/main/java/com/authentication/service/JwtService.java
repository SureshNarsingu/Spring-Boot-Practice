package com.authentication.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${security.jwt.secret-key}")
    private String secretKey;
	
	@Value("${security.jwt.expiration-time}")
    private long jwtExpiration;
	
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	private String generateToken(Map<String,Object> extractClamis, UserDetails userDetails) {
		return buildToken(extractClamis, userDetails, jwtExpiration);
	}
	
	

	private String buildToken(Map<String, Object> extractClamis, UserDetails userDetails, long jwtExpiration) {
		return Jwts.builder().setClaims(extractClamis).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public <T> T extractClamis(String token, Function<Claims, T> claimsReslover) {
		Claims clamis = extractAllClamis(token);
		return claimsReslover.apply(clamis);
	}

	private Claims extractAllClamis(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String extractUserName(String token) {
		return extractClamis(token, Claims::getSubject);
	}
	
	public long getExpirationTime() {
		return jwtExpiration;
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token);
	}

	private boolean extractExpiration(String token) {
		return extractClamis(token, Claims::getExpiration).before(new Date());
	}

}
