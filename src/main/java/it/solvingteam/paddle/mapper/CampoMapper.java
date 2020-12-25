package it.solvingteam.paddle.mapper;

import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.CampoDTO;
import it.solvingteam.paddle.model.Campo;

@Component
public class CampoMapper extends AbstractMapper<Campo, CampoDTO> {

	@Override
	public CampoDTO convertEntityToDto(Campo entity) {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

	@Override
	public Campo convertDtoToEntity(CampoDTO dto) throws Exception {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

}
