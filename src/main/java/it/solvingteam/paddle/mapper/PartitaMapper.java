package it.solvingteam.paddle.mapper;

import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.PartitaDTO;
import it.solvingteam.paddle.model.Partita;

@Component
public class PartitaMapper extends AbstractMapper<Partita, PartitaDTO>  {

	@Override
	public PartitaDTO convertEntityToDto(Partita entity) {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

	@Override
	public Partita convertDtoToEntity(PartitaDTO dto) throws Exception {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

}
