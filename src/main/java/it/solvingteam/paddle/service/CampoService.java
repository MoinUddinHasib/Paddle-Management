package it.solvingteam.paddle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.paddle.dto.CampoDTO;
import it.solvingteam.paddle.mapper.CampoMapper;
import it.solvingteam.paddle.model.Campo;
import it.solvingteam.paddle.repository.CampoRepository;

@Service
public class CampoService {

	@Autowired
	private CampoRepository campoRepository;
	    
	@Autowired
	private CampoMapper campoMapper;
	    
    public List<CampoDTO> findAll() {
        List<Campo> allCampi = this.campoRepository.findAll();
        return campoMapper.convertEntityToDto(allCampi);
    }

    public CampoDTO getById(String id) throws Exception {
    	if(id==null || isNaN(id)) {
    		throw new Exception("id non valido");
    	}
    	Campo c= campoRepository.findById(Integer.parseInt(id)).orElse(null);
    	if(c==null) {
    		throw new Exception("Campo non trovato");
    	}
    	return campoMapper.convertEntityToDto(c);
    }
    
    public void delete(String id) throws Exception {
    	CampoDTO c = this.getById(id);
    	campoRepository.delete(campoMapper.convertDtoToEntity(c));
    }

	public CampoDTO inserisci(CampoDTO campoDTO) throws Exception {
		if(campoDTO == null) {
			throw new Exception("Input non valido");
		}
		if(campoDTO.getId()!=null) {
			throw new Exception("Id deve essere null");
		}
		
		return this.getById(campoRepository.save(campoMapper.convertDtoToEntity(campoDTO)).getId().toString());
	}

	public CampoDTO aggiorna(CampoDTO campoDTO) throws Exception {
		if(campoDTO == null) {
			throw new Exception("Input non valido");
		}
		this.getById(campoDTO.getId());	
		return this.getById(campoRepository.save(campoMapper.convertDtoToEntity(campoDTO)).getId().toString());		
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
