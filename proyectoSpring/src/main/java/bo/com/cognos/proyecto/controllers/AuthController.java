package bo.com.cognos.proyecto.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bo.com.cognos.proyecto.security.rest.AuthenticationRequest;
import bo.com.cognos.proyecto.security.rest.JwtTokenProvider;

@RestController
public class AuthController {
    @Autowired   @Qualifier("authenticationManagerBean")
    AuthenticationManager authenticationManager;	
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @PostMapping("/rest/login")
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) {
        try {
            String username = data.getUsername();
            Authentication auth = authenticationManager.authenticate(
            		new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = auth.getAuthorities().stream().
            		map(r -> r.getAuthority()).collect(Collectors.toList());
            String token = jwtTokenProvider.createToken(username, roles);
            Map<Object, Object> model = new HashMap<Object, Object>();
            model.put("username", username);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}

