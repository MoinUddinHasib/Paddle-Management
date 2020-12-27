package it.solvingteam.paddle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.paddle.dto.CampoDTO;
import it.solvingteam.paddle.mapper.CampoMapper;
import it.solvingteam.paddle.model.Campo;
import it.solvingteam.paddle.model.Circolo;
import it.solvingteam.paddle.model.CircoloUtente;
import it.solvingteam.paddle.repository.CampoRepository;
import it.solvingteam.paddle.repository.CircoloUtenteRepository;

@Service
public class CampoService {

	@Autowired
	private CampoRepository campoRepository;
	
	@Autowired
	private CircoloUtenteRepository circoloutenteRepository;
	    
	@Autowired
	private CampoMapper campoMapper;
	
	public List<CampoDTO> getAllMyCampi(String idu) {
		List<Campo> allCampi = this.campoRepository.findAllMy(idu);
        return campoMapper.convertEntityToDto(allCampi);
	}

    private CampoDTO getById(String id) throws Exception {
    	if(id==null || isNaN(id)) {
    		throw new Exception("id non valido");
    	}
    	Campo c= campoRepository.findById(Integer.parseInt(id)).orElse(null);
    	if(c==null) {
    		throw new Exception("Campo non trovato");
    	}
    	return campoMapper.convertEntityToDto(c);
    }
    
	public CampoDTO inserisci(CampoDTO campoDTO, String idu) throws Exception {
		if(campoDTO == null || campoDTO.getNome()==null || campoDTO.getNome().isEmpty() || idu==null || isNaN(idu)) {
			throw new Exception("Input non valido");
		}
		CircoloUtente cu = circoloutenteRepository.findCircoloApprovatoByUtente(Integer.parseInt(idu)).orElse(null);
		if(cu==null) {
			throw new Exception("L'utente non ha un circolo");
		}
		Circolo c = cu.getCircolo();
		Campo campo =new Campo();
		campo.setNome(campoDTO.getNome());
		campo.setCircolo(c);
		campo.setAperto(true);
		
		return this.getById(campoRepository.save(campo).getId().toString());
	}
//
//	public CampoDTO aggiorna(CampoDTO campoDTO) throws Exception {
//		if(campoDTO == null) {
//			throw new Exception("Input non valido");
//		}
//		this.getById(campoDTO.getId());	
//		return this.getById(campoRepository.save(campoMapper.convertDtoToEntity(campoDTO)).getId().toString());		
//	}
//
	private boolean isNaN(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return true;
		}
		return false;
	}


}
