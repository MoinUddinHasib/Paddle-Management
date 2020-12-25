package it.solvingteam.paddle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.paddle.dto.SlotDTO;
import it.solvingteam.paddle.mapper.SlotMapper;
import it.solvingteam.paddle.model.Slot;
import it.solvingteam.paddle.repository.SlotRepository;

@Service
public class SlotService {

	@Autowired
	private SlotRepository slotRepository;
	    
	@Autowired
	private SlotMapper slotMapper;
	    
    public List<SlotDTO> findAll() {
        List<Slot> allSlots = this.slotRepository.findAll();
        return slotMapper.convertEntityToDto(allSlots);
    }

    public SlotDTO getById(String id) throws Exception {
    	if(id==null || isNaN(id)) {
    		throw new Exception("id non valido");
    	}
    	Slot c= slotRepository.findById(Integer.parseInt(id)).orElse(null);
    	if(c==null) {
    		throw new Exception("Slot non trovato");
    	}
    	return slotMapper.convertEntityToDto(c);
    }
    
    public void delete(String id) throws Exception {
    	SlotDTO c = this.getById(id);
    	slotRepository.delete(slotMapper.convertDtoToEntity(c));
    }

	public SlotDTO inserisci(SlotDTO slotDTO) throws Exception {
		if(slotDTO == null) {
			throw new Exception("Input non valido");
		}
		if(slotDTO.getId()!=null) {
			throw new Exception("Id deve essere null");
		}
		
		return this.getById(slotRepository.save(slotMapper.convertDtoToEntity(slotDTO)).getId().toString());
	}

	public SlotDTO aggiorna(SlotDTO slotDTO) throws Exception {
		if(slotDTO == null) {
			throw new Exception("Input non valido");
		}
		this.getById(slotDTO.getId());	
		return this.getById(slotRepository.save(slotMapper.convertDtoToEntity(slotDTO)).getId().toString());		
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
