package it.solvingteam.paddle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.paddle.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

}
