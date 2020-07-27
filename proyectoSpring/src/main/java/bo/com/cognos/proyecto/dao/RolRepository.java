package bo.com.cognos.proyecto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import bo.com.cognos.proyecto.entidades.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
	

}
