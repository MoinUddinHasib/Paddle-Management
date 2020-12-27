package it.solvingteam.paddle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.paddle.model.Partita;

public interface PartitaRepository extends JpaRepository<Partita, Integer> {

	@Query("SELECT DISTINCT e FROM Partita e WHERE p.campo.id=?1 and p.data=?2")
	List<Partita> findAllByCampo(String idc, String data);

}
