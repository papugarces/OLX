package co.com.eam.avanzada.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.avanzada.domain.Chat;

@Repository

public interface IChatRepository extends CrudRepository<Chat,Integer> {
	
	
}
