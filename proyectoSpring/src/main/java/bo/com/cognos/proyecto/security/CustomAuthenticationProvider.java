package bo.com.cognos.proyecto.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import bo.com.cognos.proyecto.entidades.Rol;
import bo.com.cognos.proyecto.entidades.Usuario;
import bo.com.cognos.proyecto.service.UsuarioService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = (String)authentication.getPrincipal();
		String password = (String)authentication.getCredentials();
		Usuario usuario = usuarioService.autenticar(username, password);
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		for(Rol rol: usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(rol.getRol()));
		}
		
		User user = new User(username, password, roles);
		
		return new UsernamePasswordAuthenticationToken(user, password, roles);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// tipo de autenticación que necesita spring
		return true;
	}

}
