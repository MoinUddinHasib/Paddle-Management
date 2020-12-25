package it.solvingteam.paddle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.paddle.dto.PartitaUtenteDTO;
import it.solvingteam.paddle.mapper.PartitaUtenteMapper;
import it.solvingteam.paddle.model.PartitaUtente;
import it.solvingteam.paddle.repository.PartitaUtenteRepository;

@Service
public class PartitaUtenteService {

	@Autowired
	private PartitaUtenteRepository partitaUtenteRepository;
	    
	@Autowired
	private PartitaUtenteMapper partitaUtenteMapper;
	    
    public List<PartitaUtenteDTO> findAll() {
        List<PartitaUtente> allCircoliUtenti = this.partitaUtenteRepository.findAll();
        return partitaUtenteMapper.convertEntityToDto(allCircoliUtenti);
    }

    public PartitaUtenteDTO getById(String pid, String uid) throws Exception {
    	if(pid==null || isNaN(pid) || uid==null || isNaN(uid)) {
    		throw new Exception("id non valido");
    	}
    	PartitaUtente l= partitaUtenteRepository.findByPartitaAndUtente(Integer.parseInt(pid), Integer.parseInt(uid)).orElse(null);
    	if(l==null) {
    		throw new Exception("PartitaUtente non trovato");
    	}
    	return partitaUtenteMapper.convertEntityToDto(l);
    }
    
    public void delete(String cid, String uid) throws Exception {
    	PartitaUtenteDTO l = this.getById(cid,uid);
    	partitaUtenteRepository.delete(partitaUtenteMapper.convertDtoToEntity(l));
    }

	public PartitaUtenteDTO inserisci(PartitaUtenteDTO partitaUtenteDTO) throws Exception {
		if(partitaUtenteDTO == null || partitaUtenteDTO.getIdPartita()==null || partitaUtenteDTO.getIdUtente()==null) {
			throw new Exception("Input non valido");
		}
		PartitaUtente pu = partitaUtenteRepository.save(partitaUtenteMapper.convertDtoToEntity(partitaUtenteDTO));
		return this.getById(pu.getPartita().getId().toString(),pu.getUtente().getId().toString());
	}

	public PartitaUtenteDTO aggiorna(PartitaUtenteDTO partitaUtenteDTO) throws Exception {
		return inserisci(partitaUtenteDTO);	
	}

	private boolean isNaN(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return true;
		}
		return false;
	}

}
