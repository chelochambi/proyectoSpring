package bo.com.cognos.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import bo.com.cognos.proyecto.dao.UsuarioRepository;
import bo.com.cognos.proyecto.entidades.Usuario;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repository;
	
	public Usuario autenticar(String login, String password) {
		
		Usuario usuario = repository.findByLogin(login);
		if(usuario == null) {
			throw new UsernameNotFoundException("Nombre de usuario no encontrado");
		}
		/*if(usuario.getPassword().equals(password)) {
			throw new BadCredentialsException("Contraseña incorrecta");
		}*/
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		if(!bCryptPasswordEncoder.matches(password, usuario.getPassword())) {
			throw new BadCredentialsException("Contraseña incorrecta");
		}
		
		return usuario;		
	}
}
