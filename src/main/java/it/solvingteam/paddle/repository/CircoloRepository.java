package it.solvingteam.paddle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.paddle.model.Circolo;

public interface CircoloRepository extends JpaRepository<Circolo, Integer> {

	@Query("SELECT DISTINCT c FROM Circolo c join CircoloUtente cu WHERE cu.stato = 'APPROVATO'")
	List<Circolo> findAllCircoliApprovati();

}
