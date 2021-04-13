package fr.glelouet.spring.testhibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.glelouet.spring.testhibernate.model.Client;

public interface ClientRepo extends CrudRepository<Client, Long> {

	@Query("select c from Client c where lower(c.firstName) like lower('%'||?1||'%') or lower(c.lastName) like lower('%'||?1||'%')")
	List<Client> findMatching(String filter);

}
