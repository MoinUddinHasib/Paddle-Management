package it.solvingteam.paddle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.paddle.dto.NotiziaDTO;
import it.solvingteam.paddle.mapper.NotiziaMapper;
import it.solvingteam.paddle.model.Notizia;
import it.solvingteam.paddle.repository.NotiziaRepository;

@Service
public class NotiziaService {

	@Autowired
	private NotiziaRepository notiziaRepository;
	    
	@Autowired
	private NotiziaMapper notiziaMapper;
	    
    public List<NotiziaDTO> findAll() {
        List<Notizia> allNotizie = this.notiziaRepository.findAll();
        return notiziaMapper.convertEntityToDto(allNotizie);
    }

    public NotiziaDTO getById(String id) throws Exception {
    	if(id==null || isNaN(id)) {
    		throw new Exception("id non valido");
    	}
    	Notizia c= notiziaRepository.findById(Integer.parseInt(id)).orElse(null);
    	if(c==null) {
    		throw new Exception("Notizia non trovata");
    	}
    	return notiziaMapper.convertEntityToDto(c);
    }
    
    public void delete(String id) throws Exception {
    	NotiziaDTO c = this.getById(id);
    	notiziaRepository.delete(notiziaMapper.convertDtoToEntity(c));
    }

	public NotiziaDTO inserisci(NotiziaDTO notiziaDTO) throws Exception {
		if(notiziaDTO == null) {
			throw new Exception("Input non valida");
		}
		if(notiziaDTO.getId()!=null) {
			throw new Exception("Id deve essere null");
		}
		
		return this.getById(notiziaRepository.save(notiziaMapper.convertDtoToEntity(notiziaDTO)).getId().toString());
	}

	public NotiziaDTO aggiorna(NotiziaDTO notiziaDTO) throws Exception {
		if(notiziaDTO == null) {
			throw new Exception("Input non valida");
		}
		this.getById(notiziaDTO.getId());	
		return this.getById(notiziaRepository.save(notiziaMapper.convertDtoToEntity(notiziaDTO)).getId().toString());		
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
