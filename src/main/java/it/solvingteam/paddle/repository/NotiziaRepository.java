package it.solvingteam.paddle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.paddle.model.Notizia;

public interface NotiziaRepository extends JpaRepository<Notizia, Integer> {

}
