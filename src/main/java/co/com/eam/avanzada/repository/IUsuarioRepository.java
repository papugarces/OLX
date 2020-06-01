package co.com.eam.avanzada.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.avanzada.domain.Usuario;

@Repository

public interface IUsuarioRepository extends CrudRepository<Usuario , String> {
	
	
	public Optional<Usuario> findByCorreo(String correo);
	
	
}
                                       