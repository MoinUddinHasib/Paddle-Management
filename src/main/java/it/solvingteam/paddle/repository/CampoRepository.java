package it.solvingteam.paddle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.paddle.model.Campo;

public interface CampoRepository extends JpaRepository<Campo, Integer> {
	
	@Query("SELECT DISTINCT e FROM CircoloUtente p inner join Campo c on p.circolo.id=c.circolo.id WHERE p.tipo = 'CREAZIONE' and p.stato = 'APPROVATO' and p.utente.id = ?1")
	List<Campo> findAllMy(String idu);


}
