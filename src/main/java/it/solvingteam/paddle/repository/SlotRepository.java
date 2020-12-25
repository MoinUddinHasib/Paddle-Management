package it.solvingteam.paddle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.paddle.model.Slot;

public interface SlotRepository extends JpaRepository<Slot, Integer> {

}
