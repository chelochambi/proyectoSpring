package bo.com.cognos.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import bo.com.cognos.proyecto.service.UsuarioService;

@Component
public class ConsoleListener implements CommandLineRunner {

	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		usuarioService.inicializar();
	}

}
