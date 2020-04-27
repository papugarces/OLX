package co.com.eam.avanzada.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.avanzada.domain.Departamento;

@Repository

public interface IDepartamentoRepository extends CrudRepository<Departamento, Integer> {
	
	
}
