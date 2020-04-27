package co.com.eam.avanzada.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.avanzada.domain.Categoria;

@Repository
public interface ICategoriaRepository extends CrudRepository<Categoria , Integer> {
	
	
}
