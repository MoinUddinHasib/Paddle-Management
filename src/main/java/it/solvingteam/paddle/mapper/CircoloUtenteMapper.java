package it.solvingteam.paddle.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.CircoloUtenteDTO;
import it.solvingteam.paddle.model.CircoloUtente;
import it.solvingteam.paddle.repository.CircoloRepository;
import it.solvingteam.paddle.repository.UtenteRepository;

@Component
public class CircoloUtenteMapper extends AbstractMapper<CircoloUtente, CircoloUtenteDTO> {
	
    @Autowired
    private UtenteRepository utenteRepository;
    
    @Autowired
    private CircoloRepository circoloRepository;

	@Override
	public CircoloUtenteDTO convertEntityToDto(CircoloUtente entity) {
		if(entity==null) 
			return null;
		
		CircoloUtenteDTO cuDto = new CircoloUtenteDTO();
		cuDto.setIdCircolo(entity.getCircolo().getId().toString());
		cuDto.setIdUtente(entity.getUtente().getId().toString());
		cuDto.setStato(entity.getStato().toString());
		cuDto.setTipo(entity.getTipo().toString());
		return cuDto;
	}

	@Override
	public CircoloUtente convertDtoToEntity(CircoloUtenteDTO dto) throws Exception {
		if(dto==null)
			return null;
		
		CircoloUtente cu = new CircoloUtente();

		cu.setCircolo(circoloRepository.getOne(Integer.parseInt(dto.getIdCircolo())));
		cu.setUtente(utenteRepository.getOne(Integer.parseInt(dto.getIdUtente())));
		cu.setStato(CircoloUtente.Stato.valueOf(dto.getStato()));
		cu.setTipo(CircoloUtente.Tipo.valueOf(dto.getTipo()));
		return cu;
	}

}
