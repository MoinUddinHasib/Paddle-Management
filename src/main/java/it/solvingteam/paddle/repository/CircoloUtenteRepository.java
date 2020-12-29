package it.solvingteam.paddle.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.paddle.model.CircoloUtente;

public interface CircoloUtenteRepository extends JpaRepository<CircoloUtente, Integer> {
	
	@Query("FROM CircoloUtente e WHERE e.utente.id = ?1 and e.tipo = 'CREAZIONE' and e.stato = 'APPROVATO'")
	Optional<CircoloUtente> findCircoloApprovatoByUtente(Integer uid);
	
	@Query("FROM CircoloUtente e WHERE e.circolo.id = ?1 and e.utente.id = ?2 and e.tipo = 'CREAZIONE'")
	Optional<CircoloUtente> findCreazioneByCircoloAndUtente(Integer cid, Integer uid);
	
	@Query("FROM CircoloUtente e WHERE e.stato = 'IN_LAVORAZIONE' and e.tipo = 'CREAZIONE'")
	List<CircoloUtente> findAllCreazioniInLavorazione();

	@Query("FROM CircoloUtente e WHERE e.circolo.id = ?1 and e.stato = 'APPROVATO' and e.tipo = 'CREAZIONE'")
	Optional<CircoloUtente> findByCircoloApprovatoById(Integer cid);
	
	@Query("FROM CircoloUtente e WHERE e.utente.id = ?1 and e.tipo = 'ISCRIZIONE'")
	Optional<CircoloUtente> findIscrizioneByUtente(Integer uid);
	
	@Query("FROM CircoloUtente e WHERE e.utente.id = ?1 and e.tipo = 'CREAZIONE' and e.stato != 'NON_APPROVATO'")
	Optional<CircoloUtente> findCircoloNotRifiutatoByUtente(Integer uid);

	@Query("FROM CircoloUtente e WHERE e.circolo.id = ?1 and e.tipo = 'ISCRIZIONE'")
	List<CircoloUtente> findAllIscrizzioniByIdu(Integer cid);

	@Query("FROM CircoloUtente e WHERE e.circolo.id = ?1 and e.utente.id = ?2 and e.tipo = 'ISCRIZIONE'")
	CircoloUtente getIscrizioneByCircoloAndGuest(Integer idc, Integer idu);

}
