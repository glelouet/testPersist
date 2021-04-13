package fr.glelouet.spring.testhibernate.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.glelouet.spring.testhibernate.model.Bakery;

public interface BakeryRepo extends CrudRepository<Bakery	, Long>{

	List<Bakery> findByNameIgnoreCase(String name);

}
