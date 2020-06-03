package co.com.eam.avanzada.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.eam.avanzada.domain.Municipio;

@Repository

public interface IMunicipioRepository extends CrudRepository<Municipio, Integer> {
	
	@Query("SELECT m FROM Municipio m join m.departamento d WHERE d.id = :idDepartamento")
	 List<Municipio> findAllMunicipiosByIdDepartamento(@Param("idDepartamento") Integer idDepartamento);
}
