package it.solvingteam.paddle.service;

import java.util.regex.Pattern;

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
	    
	public UtenteDTO login(UtenteDTO utenteDTO) throws Exception {
		if(utenteDTO==null) {
			throw new Exception("Input non valido");
		}
		Utente u= utenteRepository.findByEmailAndPassword(utenteDTO.getEmail(),utenteDTO.getPassword()).orElse(null);
		if(u==null) {
    		throw new Exception("Credenziali sbagliate");
    	}
    	return utenteMapper.convertEntityToDto(u);
	}

	public UtenteDTO inserisci(UtenteDTO utenteDTO) throws Exception {
		if(utenteDTO == null || isNaU(utenteDTO)) {
			throw new Exception("Alcuni campi non sono validi");
		}
		utenteDTO.setRuolo(Utente.Ruolo.GUEST_ROLE.toString());
		//TODO FOTO
		return this.getById(utenteRepository.save(utenteMapper.convertDtoToEntity(utenteDTO)).getId().toString());
	}

	private UtenteDTO getById(String id){
    	return utenteMapper.convertEntityToDto(utenteRepository.findById(Integer.parseInt(id)).orElse(null));
    }
	
    private boolean isNaU(UtenteDTO utenteDTO) {
		if(!matchNome(utenteDTO.getNome()) || !matchNome(utenteDTO.getCognome()) ||
				!matchData(utenteDTO.getDataNascita()) || !matchEmail(utenteDTO.getEmail()) ||
				!matchCellulare(utenteDTO.getCellulare()) || utenteDTO.getPassword().isEmpty())
			return true;
		return false;
	}
    
    private boolean matchCellulare(String field) {
		return Pattern.matches("\\d{10}",field);
	}

	private boolean matchEmail(String field) {
    	return Pattern.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",field);
    }

    private boolean matchData(String field) {
    	return Pattern.matches("\\d{4}-[01]\\d-[0-3]\\d",field);
    }
    
    private boolean matchNome(String field) {
    	return Pattern.matches("^[A-Za-z][a-zA-Z\\s]*$",field);
    }
    
}
