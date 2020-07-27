package bo.com.cognos.proyecto.security.rest;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	private String secretKey = "secret";
	private long validityInMilliseconds = 3600000; // 1h
	@PostConstruct
	protected void init() {
	    secretKey = 
	 Base64.getEncoder().encodeToString(secretKey.getBytes());
	 }
	
	public String createToken(String username, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("roles", roles);
		Date now = new Date();
		Date validity = new Date(now.getTime() + 
		                                validityInMilliseconds);
		return Jwts.builder().setClaims(claims)//
		   .setIssuedAt(now).setExpiration(validity)//
		      .signWith(SignatureAlgorithm.HS256, secretKey)//
		            .compact();
		}
	
	public Authentication getAuthentication(String token) {
		String username = getUsername(token);
		Jws<Claims> claims = 
	Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		List<String> rols = (List<String>)claims.getBody().get("roles");
		List<GrantedAuthority> auths = rols.stream().map(r -> new SimpleGrantedAuthority(r)). collect(Collectors.toList());
		return new UsernamePasswordAuthenticationToken(username, "", auths);
	}

	public String getUsername(String token) {
	    return Jwts.parser().setSigningKey(secretKey).
	         parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
	  String bearerToken = req.getHeader("Authorization");
	  if (bearerToken != null && 
	               bearerToken.startsWith("Bearer ")) {
	      return bearerToken.substring(7, bearerToken.length());
	  }
	  return null;
	}
	
	public boolean validateToken(String token) {
		  try {
		    Jws<Claims> claims = Jwts.parser().
		         setSigningKey(secretKey).parseClaimsJws(token);
		    if (claims.getBody().getExpiration().before(new Date())){
		       return false;
		     }
		     return true;
		  } catch (JwtException | IllegalArgumentException e) {
		     throw new RuntimeException("Expired or invalid JWT token");
		  }
		}



}
