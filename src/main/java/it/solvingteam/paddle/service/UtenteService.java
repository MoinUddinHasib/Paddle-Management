package it.solvingteam.paddle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.paddle.dto.UtenteDTO;
import it.solvingteam.paddle.mapper.UtenteMapper;
import it.solvingteam.paddle.model.Utente;
import it.solvingteam.paddle.repository.UtenteRepository;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepository utenteRepository;
	    
	@Autowired
	private UtenteMapper utenteMapper;
	    
    public List<UtenteDTO> findAll() {
        List<Utente> allUtenti = this.utenteRepository.findAll();
        return utenteMapper.convertEntityToDto(allUtenti);
    }

    public UtenteDTO getById(String id) throws Exception {
    	if(id==null || isNaN(id)) {
    		throw new Exception("id non valido");
    	}
    	Utente c= utenteRepository.findById(Integer.parseInt(id)).orElse(null);
    	if(c==null) {
    		throw new Exception("Utente non trovato");
    	}
    	return utenteMapper.convertEntityToDto(c);
    }
    
    public void delete(String id) throws Exception {
    	UtenteDTO c = this.getById(id);
    	utenteRepository.delete(utenteMapper.convertDtoToEntity(c));
    }

	public UtenteDTO inserisci(UtenteDTO utenteDTO) throws Exception {
		if(utenteDTO == null) {
			throw new Exception("Input non valido");
		}
		if(utenteDTO.getId()!=null) {
			throw new Exception("Id deve essere null");
		}
		
		return this.getById(utenteRepository.save(utenteMapper.convertDtoToEntity(utenteDTO)).getId().toString());
	}

	public UtenteDTO aggiorna(UtenteDTO utenteDTO) throws Exception {
		if(utenteDTO == null) {
			throw new Exception("Input non valido");
		}
		this.getById(utenteDTO.getId());	
		return this.getById(utenteRepository.save(utenteMapper.convertDtoToEntity(utenteDTO)).getId().toString());		
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
