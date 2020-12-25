package it.solvingteam.paddle.mapper;

import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.UtenteDTO;
import it.solvingteam.paddle.model.Utente;

@Component
public class UtenteMapper extends AbstractMapper<Utente, UtenteDTO> {

	@Override
	public UtenteDTO convertEntityToDto(Utente entity) {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

	@Override
	public Utente convertDtoToEntity(UtenteDTO dto) throws Exception {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

}
