package br.com.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.curso.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
