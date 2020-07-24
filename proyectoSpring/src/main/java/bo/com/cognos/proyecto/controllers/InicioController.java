package bo.com.cognos.proyecto.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bo.com.cognos.proyecto.entidades.Alumno;

@Controller
@RequestMapping("/")
public class InicioController {

	@GetMapping({"/", ""})
	public String inicio(Model model) {
		return "inicio";
	}
	
}
