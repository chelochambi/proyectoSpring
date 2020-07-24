package bo.com.cognos.proyecto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bo.com.cognos.proyecto.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query("select u from Usuario u left join fetch u.roles where u.login = :login")
	Usuario findByLogin(@Param("login") String login);
}
