package bo.com.cognos.proyecto.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bo.com.cognos.proyecto.entidades.Alumno;
import bo.com.cognos.proyecto.service.AlumnoService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	
	@Autowired
	AlumnoService alumnoService;
	
	@GetMapping({"/", ""})
	public String ListaAlumnos(Model model) {
		List<Alumno> alumnos = alumnoService.listar();
		model.addAttribute("alumnos", alumnos);
		return "listar";
	}

	@GetMapping("/agregar")
	public String preparCrearPersona(Model model) {
		model.addAttribute("alumno", new Alumno());
		return "formulario";
	}
	
	@GetMapping("/editar/{id}")
	public String preparEditarPersona(Model model, @PathVariable Integer id) {
		Alumno alumno = alumnoService.obtener(id);
		model.addAttribute("alumno", alumno);
		return "formulario";
	}
	
	@PostMapping("/guardar")
	public String guardarPersona(Model model, @ModelAttribute("alumno")Alumno alumno, HttpServletRequest request) {
		if(request.getParameter("Cancelar") == null) {
			if(alumno.getId() != null) {
				//PersonaService.getInstance().editar(persona);
				alumnoService.editar(alumno);
			}
			else {
				//PersonaService.getInstance().agregar(persona);
				alumnoService.agregar(alumno);
			}
		}
		//model.addAttribute("personas", PersonaService.getInstance().listar());
		//model.addAttribute("alumno", alumnoService.listar());
		return "redirect:/alumno/";
	}
}
