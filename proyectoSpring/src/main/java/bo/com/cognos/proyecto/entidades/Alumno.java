package bo.com.cognos.proyecto.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "alumnos")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String ci;
	
	private String nombre;
	
	private String curso;
	
	
}
