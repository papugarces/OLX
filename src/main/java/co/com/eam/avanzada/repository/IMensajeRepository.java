package co.com.eam.avanzada.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.avanzada.domain.Mensaje;
import co.com.eam.avanzada.domain.MensajePK;
@Repository

public interface IMensajeRepository extends CrudRepository<Mensaje, MensajePK> {
	
	//---------cargar los mensajes de un chat----------------s
	
	@Query("SELECT ms "
			+ "FROM Mensaje ms JOIN ms.id.chat c JOIN ms.usuario us "
			+ "WHERE c.usuario1.id.dni = ?1 AND c.usuario2.id.dni = ?2")
	public Iterable<Mensaje> cargarMensajes(String dniCliente, String dniOfertador);
	
	
}