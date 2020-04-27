package co.com.eam.avanzada.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import co.com.eam.avanzada.domain.Usuario;
import co.com.eam.avanzada.domain.UsuarioPK;

@Repository

public interface IUsuarioRepository extends CrudRepository<Usuario , UsuarioPK> {
	
	
}
                                       