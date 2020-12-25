package it.solvingteam.paddle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.paddle.model.PartitaUtente;

public interface PartitaUtenteRepository extends JpaRepository<PartitaUtente, Integer> {

	@Query("FROM PartitaUtente e WHERE e.partita.id = ?1 and e.utente.id = ?2")
	Optional<PartitaUtente> findByPartitaAndUtente(Integer pid, Integer uid);
	
}
