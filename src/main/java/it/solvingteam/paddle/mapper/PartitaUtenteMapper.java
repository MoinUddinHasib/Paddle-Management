package it.solvingteam.paddle.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.PartitaUtenteDTO;
import it.solvingteam.paddle.model.PartitaUtente;
import it.solvingteam.paddle.repository.PartitaRepository;
import it.solvingteam.paddle.repository.UtenteRepository;

@Component
public class PartitaUtenteMapper extends AbstractMapper<PartitaUtente, PartitaUtenteDTO> {
	
    @Autowired
    private UtenteRepository utenteRepository;
    
    @Autowired
    private PartitaRepository partitaRepository;

	@Override
	public PartitaUtenteDTO convertEntityToDto(PartitaUtente entity) {
		if(entity==null) 
			return null;
		
		PartitaUtenteDTO pDto = new PartitaUtenteDTO();
		pDto.setIdPartita(entity.getPartita().getId().toString());
		pDto.setIdUtente(entity.getUtente().getId().toString());
		pDto.setTipo(entity.getTipo().toString());
		return pDto;
	}

	@Override
	public PartitaUtente convertDtoToEntity(PartitaUtenteDTO dto) throws Exception {
		if(dto==null)
			return null;
		
		PartitaUtente pu = new PartitaUtente();
		pu.setPartita(partitaRepository.getOne(Integer.parseInt(dto.getIdPartita())));
		pu.setUtente(utenteRepository.getOne(Integer.parseInt(dto.getIdUtente())));
		pu.setTipo(PartitaUtente.Tipo.valueOf(dto.getTipo()));
		return pu;
	}

}
