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
	
	public List<PartitaDTO> getByIdCampo(String idc, String data) {
      List<Partita> allPartite = this.partitaRepository.findAllByCampo(Integer.parseInt(idc), data);
      return partitaMapper.convertEntityToDto(allPartite);
	}

	public PartitaDTO paga(String idp, String idu) throws Exception {
		if(idp == null || idu==null || isNaN(idp) || isNaN(idu)) {
			throw new Exception("Input non valido");
		}
		Partita p = partitaRepository.findCampoByIdpIdu(Integer.parseInt(idp),Integer.parseInt(idu));
		if(p==null)
			throw new Exception("Partita non trovata");
		p.setStatoPagamento(true);
		return this.getById(partitaRepository.save(p).getId().toString());
	}
	    
//    public List<PartitaDTO> findAll() {
//        List<Partita> allPartite = this.partitaRepository.findAll();
//        return partitaMapper.convertEntityToDto(allPartite);
//    }

    private PartitaDTO getById(String id) throws Exception {
    	if(id==null || isNaN(id)) {
    		throw new Exception("id non valido");
    	}
    	Partita c= partitaRepository.findById(Integer.parseInt(id)).orElse(null);
    	if(c==null) {
    		throw new Exception("Partita non trovato");
    	}
    	return partitaMapper.convertEntityToDto(c);
    }
    
//    public void delete(String id) throws Exception {
//    	PartitaDTO c = this.getById(id);
//    	partitaRepository.delete(partitaMapper.convertDtoToEntity(c));
//    }
//
//	public PartitaDTO inserisci(PartitaDTO partitaDTO) throws Exception {
//		if(partitaDTO == null) {
//			throw new Exception("Input non valido");
//		}
//		if(partitaDTO.getId()!=null) {
//			throw new Exception("Id deve essere null");
//		}
//		
//		return this.getById(partitaRepository.save(partitaMapper.convertDtoToEntity(partitaDTO)).getId().toString());
//	}
//
//	public PartitaDTO aggiorna(PartitaDTO partitaDTO) throws Exception {
//		if(partitaDTO == null) {
//			throw new Exception("Input non valido");
//		}
//		this.getById(partitaDTO.getId());	
//		return this.getById(partitaRepository.save(partitaMapper.convertDtoToEntity(partitaDTO)).getId().toString());		
//	}

	private boolean isNaN(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return true;
		}
		return false;
	}

}
