package bo.com.cognos.proyecto.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.com.cognos.proyecto.dao.AlumnoRepository;
import bo.com.cognos.proyecto.entidades.Alumno;

@Service
@Transactional(readOnly = true)
public class AlumnoService {
	
	@Autowired
	AlumnoRepository repository;
	
	public List<Alumno> listar(){
		return repository.findAll();
	}
	
	public Alumno obtener(Integer id){
		return repository.getOne(id);
	}
	
	@Transactional
	public void agregar(Alumno alumno) {
		repository.save(alumno);
	}
	
	@Transactional
	public void editar(Alumno alumnoEdicion) {
		repository.saveAndFlush(alumnoEdicion);
	}
	
	@Transactional
	public void eliminar(Integer idEliminar) {
		repository.deleteById(idEliminar);
	}	
	
}
