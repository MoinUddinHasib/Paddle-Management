package it.solvingteam.paddle.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.paddle.dto.CampoDTO;
import it.solvingteam.paddle.mapper.CampoMapper;
import it.solvingteam.paddle.model.Campo;
import it.solvingteam.paddle.model.Circolo;
import it.solvingteam.paddle.model.CircoloUtente;
import it.solvingteam.paddle.model.Partita;
import it.solvingteam.paddle.model.Slot;
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
	
	public List<CampoDTO> getAllMyCampi(String idu) throws Exception {
		if(idu==null || isNaN(idu)) {
    		throw new Exception("id non valido");
    	}
		List<Campo> allCampi = this.campoRepository.findAllMy(Integer.parseInt(idu));
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

	public CampoDTO aggiorna(CampoDTO campoDTO, String idu) throws Exception {
		if(campoDTO == null || campoDTO.getNome()==null || campoDTO.getNome().isEmpty() || campoDTO.getId()==null || isNaN(campoDTO.getId())
				|| idu==null || isNaN(idu)) {
			throw new Exception("Input non valido");
		}
		Campo c =campoRepository.getByIdCampoAndUtente(Integer.parseInt(campoDTO.getId()),Integer.parseInt(idu));
		if(c==null) {
			throw new Exception("Campo inesistente");
		}
		if(controllaPartite(c.getPartite())) {
			throw new Exception("Non è possibile modificare");
		}
		c.setNome(campoDTO.getNome());
		return this.getById(campoRepository.save(c).getId().toString());		
	}

	private boolean isNaN(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return true;
		}
		return false;
	}

	private boolean controllaPartite(List<Partita> partite) {
		LocalDateTime ldt=LocalDateTime.now();
		for(Partita p : partite) {
			if(ldt.toLocalDate().compareTo(p.getData())<0) {
				return true;
			}
			Slot s = p.getSlots().get(0);
			if(ldt.toLocalDate().compareTo(p.getData())==0 && ldt.getHour()<s.getOra() && (s.getMinuti()-ldt.getMinute())>30) {
				return true;
			}
		}
		return false;
	}

	public CampoDTO dismetti(String idu, String idc) throws Exception {
		if(idc == null || isNaN(idc) || idu==null || isNaN(idu)) {
			throw new Exception("Input non valido");
		}
		Campo c =campoRepository.getByIdCampoAndUtente(Integer.parseInt(idc),Integer.parseInt(idu));
		if(c==null) {
			throw new Exception("Campo inesistente");
		}
		if(controllaPartite(c.getPartite())) {
			throw new Exception("Non è possibile modificare");
		}
		c.setAperto(false);
		return this.getById(campoRepository.save(c).getId().toString());
	}


}
