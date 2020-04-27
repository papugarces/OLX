package co.com.eam.avanzada.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.avanzada.domain.Imgproducto;

@Repository

public interface IImgProductoRepository extends CrudRepository<Imgproducto, Integer> {
	
	
}
