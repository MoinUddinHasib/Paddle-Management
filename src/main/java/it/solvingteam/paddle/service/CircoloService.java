package it.solvingteam.paddle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.paddle.dto.CircoloDTO;
import it.solvingteam.paddle.mapper.CircoloMapper;
import it.solvingteam.paddle.model.Circolo;
import it.solvingteam.paddle.repository.CircoloRepository;

@Service
public class CircoloService {

	@Autowired
	private CircoloRepository circoloRepository;
	    
	@Autowired
	private CircoloMapper circoloMapper;
	    
    public List<CircoloDTO> findAll() {
        List<Circolo> allCircoli = this.circoloRepository.findAll();
        return circoloMapper.convertEntityToDto(allCircoli);
    }

    public CircoloDTO getById(String id) throws Exception {
    	if(id==null || isNaN(id)) {
    		throw new Exception("id non valido");
    	}
    	Circolo c= circoloRepository.findById(Integer.parseInt(id)).orElse(null);
    	if(c==null) {
    		throw new Exception("Circolo non trovato");
    	}
    	return circoloMapper.convertEntityToDto(c);
    }
    
    public void delete(String id) throws Exception {
    	CircoloDTO c = this.getById(id);
    	circoloRepository.delete(circoloMapper.convertDtoToEntity(c));
    }

	public CircoloDTO inserisci(CircoloDTO circoloDTO) throws Exception {
		if(circoloDTO == null) {
			throw new Exception("Input non valido");
		}
		if(circoloDTO.getId()!=null) {
			throw new Exception("Id deve essere null");
		}
		
		return this.getById(circoloRepository.save(circoloMapper.convertDtoToEntity(circoloDTO)).getId().toString());
	}

	public CircoloDTO aggiorna(CircoloDTO circoloDTO) throws Exception {
		if(circoloDTO == null) {
			throw new Exception("Input non valido");
		}
		this.getById(circoloDTO.getId());		
		return this.getById(circoloRepository.save(circoloMapper.convertDtoToEntity(circoloDTO)).getId().toString());		
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
