package it.solvingteam.paddle.mapper;

import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.NotiziaDTO;
import it.solvingteam.paddle.model.Notizia;

@Component
public class NotiziaMapper extends AbstractMapper<Notizia, NotiziaDTO> {

	@Override
	public NotiziaDTO convertEntityToDto(Notizia entity) {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

	@Override
	public Notizia convertDtoToEntity(NotiziaDTO dto) throws Exception {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

}
