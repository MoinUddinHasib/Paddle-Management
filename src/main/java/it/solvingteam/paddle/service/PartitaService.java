package it.solvingteam.paddle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.paddle.dto.PartitaDTO;
import it.solvingteam.paddle.mapper.PartitaMapper;
import it.solvingteam.paddle.model.Partita;
import it.solvingteam.paddle.repository.PartitaRepository;

@Service
public class PartitaService {
	
	@Autowired
	private PartitaRepository partitaRepository;
	    
	@Autowired
	private PartitaMapper partitaMapper;
	    
    public List<PartitaDTO> findAll() {
        List<Partita> allPartite = this.partitaRepository.findAll();
        return partitaMapper.convertEntityToDto(allPartite);
    }

    public PartitaDTO getById(String id) throws Exception {
    	if(id==null || isNaN(id)) {
    		throw new Exception("id non valido");
    	}
    	Partita c= partitaRepository.findById(Integer.parseInt(id)).orElse(null);
    	if(c==null) {
    		throw new Exception("Partita non trovato");
    	}
    	return partitaMapper.convertEntityToDto(c);
    }
    
    public void delete(String id) throws Exception {
    	PartitaDTO c = this.getById(id);
    	partitaRepository.delete(partitaMapper.convertDtoToEntity(c));
    }

	public PartitaDTO inserisci(PartitaDTO partitaDTO) throws Exception {
		if(partitaDTO == null) {
			throw new Exception("Input non valido");
		}
		if(partitaDTO.getId()!=null) {
			throw new Exception("Id deve essere null");
		}
		
		return this.getById(partitaRepository.save(partitaMapper.convertDtoToEntity(partitaDTO)).getId().toString());
	}

	public PartitaDTO aggiorna(PartitaDTO partitaDTO) throws Exception {
		if(partitaDTO == null) {
			throw new Exception("Input non valido");
		}
		this.getById(partitaDTO.getId());	
		return this.getById(partitaRepository.save(partitaMapper.convertDtoToEntity(partitaDTO)).getId().toString());		
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
