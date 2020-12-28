package it.solvingteam.paddle.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.PartitaUtenteDTO;
import it.solvingteam.paddle.model.PartitaUtente;
import it.solvingteam.paddle.service.PartitaService;
import it.solvingteam.paddle.service.UtenteService;

@Component
public class PartitaUtenteMapper extends AbstractMapper<PartitaUtente, PartitaUtenteDTO> {
	
    @Autowired
    private UtenteService utenteService;
    
    @Autowired
    private PartitaService partitaService;
    
	@Autowired
	private PartitaMapper partitaMapper;
	
	@Autowired
	private UtenteMapper utenteMapper;

	@Override
	public PartitaUtenteDTO convertEntityToDto(PartitaUtente entity) {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

	@Override
	public PartitaUtente convertDtoToEntity(PartitaUtenteDTO dto) throws Exception {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

}
