package it.solvingteam.paddle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.paddle.model.Partita;

public interface PartitaRepository extends JpaRepository<Partita, Integer> {

	@Query("SELECT DISTINCT e FROM Partita e WHERE e.campo.id=?1 and e.data=?2")
	List<Partita> findAllByCampo(Integer idc, String data);
	
	@Query("SELECT p FROM CircoloUtente u join Circolo join Campo join Partita p WHERE p.id=?1 and u.utente.id=?2")
	Partita findCampoByIdpIdu(Integer idp, Integer idu);

}
