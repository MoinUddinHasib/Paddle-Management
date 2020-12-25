package it.solvingteam.paddle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.paddle.dto.CircoloUtenteDTO;
import it.solvingteam.paddle.mapper.CircoloUtenteMapper;
import it.solvingteam.paddle.model.CircoloUtente;
import it.solvingteam.paddle.repository.CircoloUtenteRepository;

@Service
public class CircoloUtenteService {

	@Autowired
	private CircoloUtenteRepository circoloUtenteRepository;
	    
	@Autowired
	private CircoloUtenteMapper circoloUtenteMapper;
	    
    public List<CircoloUtenteDTO> findAll() {
        List<CircoloUtente> allCircoliUtenti = this.circoloUtenteRepository.findAll();
        return circoloUtenteMapper.convertEntityToDto(allCircoliUtenti);
    }

    public CircoloUtenteDTO getById(String cid, String uid) throws Exception {
    	if(cid==null || isNaN(cid) || uid==null || isNaN(uid)) {
    		throw new Exception("id non valido");
    	}
    	CircoloUtente l= circoloUtenteRepository.findByCircoloAndUtente(Integer.parseInt(cid), Integer.parseInt(uid)).orElse(null);
    	if(l==null) {
    		throw new Exception("CircoloUtente non trovato");
    	}
    	return circoloUtenteMapper.convertEntityToDto(l);
    }
    
    public void delete(String cid, String uid) throws Exception {
    	CircoloUtenteDTO l = this.getById(cid,uid);
    	circoloUtenteRepository.delete(circoloUtenteMapper.convertDtoToEntity(l));
    }

	public CircoloUtenteDTO inserisci(CircoloUtenteDTO circoloUtenteDTO) throws Exception {
		if(circoloUtenteDTO == null || circoloUtenteDTO.getIdCircolo()==null || circoloUtenteDTO.getIdUtente()==null) {
			throw new Exception("Input non valido");
		}
		CircoloUtente cu = circoloUtenteRepository.save(circoloUtenteMapper.convertDtoToEntity(circoloUtenteDTO));
		return this.getById(cu.getCircolo().getId().toString(),cu.getUtente().getId().toString());
	}

	public CircoloUtenteDTO aggiorna(CircoloUtenteDTO circoloUtenteDTO) throws Exception {
		return inserisci(circoloUtenteDTO);	
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
