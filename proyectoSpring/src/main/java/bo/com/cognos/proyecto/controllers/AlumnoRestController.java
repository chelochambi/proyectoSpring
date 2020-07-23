package bo.com.cognos.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bo.com.cognos.proyecto.entidades.Alumno;
import bo.com.cognos.proyecto.service.AlumnoService;

@RestController
@RequestMapping("/rest/alumno")
public class AlumnoRestController {
	
	@Autowired
	AlumnoService alumnoService;
	
	@RequestMapping(value = {"/",""}, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Alumno> listar(){
		return alumnoService.listar();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Alumno obtener(@PathVariable("id") Integer id){
		return alumnoService.obtener(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Alumno crear(@RequestBody Alumno alumno) {
		alumnoService.agregar(alumno);
		return alumno;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Alumno editar(@RequestBody Alumno alumno, @PathVariable("id") Integer id) {
		alumno.setId(id);
		alumnoService.editar(alumno);
		return alumno;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String eliminar(@PathVariable("id") Integer id) {
		alumnoService.eliminar(id);
		return "OK";
	}
}
