package fr.glelouet.spring.testhibernate.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.glelouet.spring.testhibernate.model.Pastry;

public interface PastryRepository extends CrudRepository<Pastry, Long> {

	List<Pastry> findByNameIgnoreCase(String name);

	List<Pastry> findByName(String name);

	Pastry findById(long id);

}
