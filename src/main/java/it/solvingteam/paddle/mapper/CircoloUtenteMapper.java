package it.solvingteam.paddle.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.CircoloUtenteDTO;
import it.solvingteam.paddle.model.CircoloUtente;
import it.solvingteam.paddle.service.CircoloService;
import it.solvingteam.paddle.service.UtenteService;

@Component
public class CircoloUtenteMapper extends AbstractMapper<CircoloUtente, CircoloUtenteDTO> {
	
    @Autowired
    private UtenteService utenteService;
    
    @Autowired
    private CircoloService circoloService;
    
	@Autowired
	private CircoloMapper circoloMapper;
	
	@Autowired
	private UtenteMapper utenteMapper;

	@Override
	public CircoloUtenteDTO convertEntityToDto(CircoloUtente entity) {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

	@Override
	public CircoloUtente convertDtoToEntity(CircoloUtenteDTO dto) throws Exception {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

}
