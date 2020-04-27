package co.com.eam.avanzada.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.avanzada.domain.Subcategoria;

@Repository

public interface ISubcategoriaRepository extends CrudRepository<Subcategoria, Integer> {
	
	
}
