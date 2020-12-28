package it.solvingteam.paddle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.paddle.model.Campo;

public interface CampoRepository extends JpaRepository<Campo, Integer> {
	
	@Query("SELECT DISTINCT c FROM CircoloUtente p inner join Campo c on p.circolo.id=c.circolo.id WHERE p.tipo = 'CREAZIONE' and p.stato = 'APPROVATO' and p.utente.id = ?1")
	List<Campo> findAllMy(Integer idu);

	@Query("SELECT c FROM CircoloUtente p inner join Campo c on p.circolo.id=c.circolo.id WHERE p.tipo = 'CREAZIONE' and p.stato = 'APPROVATO' and c.id=?1 and p.utente.id = ?2")
	Campo getByIdCampoAndUtente(Integer idc, Integer idu);


}
