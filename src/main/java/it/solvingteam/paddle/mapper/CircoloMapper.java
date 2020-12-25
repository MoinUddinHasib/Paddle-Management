package it.solvingteam.paddle.mapper;

import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.CircoloDTO;
import it.solvingteam.paddle.model.Circolo;

@Component
public class CircoloMapper extends AbstractMapper<Circolo, CircoloDTO> {

	@Override
	public CircoloDTO convertEntityToDto(Circolo entity) {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

	@Override
	public Circolo convertDtoToEntity(CircoloDTO dto) throws Exception {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

}
