package co.com.eam.avanzada.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.avanzada.domain.Municipio;

@Repository

public interface IMunicipioRepository extends CrudRepository<Municipio, Integer> {
	
	
}
