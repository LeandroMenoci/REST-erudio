package br.com.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.curso.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Consulta personalizada para buscar um usuário pelo nome de usuário
    @Query("SELECT u FROM User u WHERE u.userName = :userName") // Corrigido espaço antes de ":" para melhorar a legibilidade
    User findByUserName(@Param("userName") String userName);
}
