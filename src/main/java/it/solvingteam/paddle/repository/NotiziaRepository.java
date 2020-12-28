package it.solvingteam.paddle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.paddle.model.Notizia;

public interface NotiziaRepository extends JpaRepository<Notizia, Integer> {

	@Query("SELECT n FROM Notizia n join Circolo c join CircoloUtente cu WHERE cu.tipo='CREAZIONE' and cu.stato='APPROVATO' and n.id=?1 and cu.utente.id=?2")
	Notizia getNotiziaByIdnIdu(Integer idn, Integer idu);

}
