package it.solvingteam.paddle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.paddle.model.CircoloUtente;

public interface CircoloUtenteRepository extends JpaRepository<CircoloUtente, Integer> {
	
	@Query("FROM CircoloUtente e WHERE e.circolo.id = ?1 and e.utente.id = ?2")
	Optional<CircoloUtente> findByCircoloAndUtente(Integer cid, Integer uid);

}
