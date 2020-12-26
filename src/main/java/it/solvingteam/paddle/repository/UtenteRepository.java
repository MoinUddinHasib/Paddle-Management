package it.solvingteam.paddle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.paddle.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

	@Query("FROM Utente e WHERE e.email = ?1 and e.password = ?2")
	Optional<Utente> findByEmailAndPassword(String email, String password);

}
