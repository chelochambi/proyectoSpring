package bo.com.cognos.proyecto.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.com.cognos.proyecto.dao.RolRepository;
import bo.com.cognos.proyecto.dao.UsuarioRepository;
import bo.com.cognos.proyecto.entidades.Rol;
import bo.com.cognos.proyecto.entidades.Usuario;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	RolRepository rolRepository;
	
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
	
	@Transactional
	public void inicializar() {
		System.out.println("Ejecutando inicializar");
		if(rolRepository.count() == 0) {
			System.out.println("Creaando datos base");
			Rol rolAdmin = new Rol(null, "ROLE_ADMIN");
			Rol rolUser = new Rol(null, "ROLE_USER");
			
			rolRepository.saveAndFlush(rolAdmin);
			rolRepository.saveAndFlush(rolUser);
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			Usuario ursAdmin = new Usuario(null, "admin", encoder.encode("admin"), new HashSet<>(Arrays.asList(rolAdmin)));
			Usuario ursUser = new Usuario(null, "user", encoder.encode("user"), new HashSet<>(Arrays.asList(rolUser)));
			Usuario ursRoot = new Usuario(null, "root", encoder.encode("root"), new HashSet<>(Arrays.asList(rolUser, rolAdmin)));
		
			repository.saveAndFlush(ursAdmin);
			repository.saveAndFlush(ursUser);
			repository.saveAndFlush(ursRoot);
		}
		else {
			System.out.println("La BD estaba inicializada");
		}
	}
}
