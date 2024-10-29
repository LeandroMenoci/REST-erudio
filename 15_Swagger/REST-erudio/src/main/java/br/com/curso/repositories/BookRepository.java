package br.com.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.curso.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
