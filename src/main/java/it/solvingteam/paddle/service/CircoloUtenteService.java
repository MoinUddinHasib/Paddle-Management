package it.solvingteam.paddle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.paddle.dto.CircoloUtenteDTO;
import it.solvingteam.paddle.mapper.CircoloUtenteMapper;
import it.solvingteam.paddle.model.CircoloUtente;
import it.solvingteam.paddle.model.Utente;
import it.solvingteam.paddle.repository.CircoloUtenteRepository;
import it.solvingteam.paddle.repository.UtenteRepository;

@Service
public class CircoloUtenteService {
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private CircoloUtenteRepository circoloUtenteRepository;
	    
	@Autowired
	private CircoloUtenteMapper circoloUtenteMapper;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
    public List<CircoloUtenteDTO> findCreazioniInLavorazione() {
        List<CircoloUtente> allCircoliUtenti = this.circoloUtenteRepository.findAllCreazioniInLavorazione();
        return circoloUtenteMapper.convertEntityToDto(allCircoliUtenti);
    }
    
	public CircoloUtenteDTO approvaCreazione(String cid, String uid) throws Exception {
		if(cid==null || isNaN(cid) || uid==null || isNaN(uid)) {
    		throw new Exception("id non validi");
    	}
    	CircoloUtente cu= circoloUtenteRepository.findCreazioneByCircoloAndUtente(Integer.parseInt(cid), Integer.parseInt(uid)).orElse(null);
    	if(cu==null) {
    		throw new Exception("Proposta non trovata");
    	}
    	CircoloUtente cu2= circoloUtenteRepository.findCircoloApprovato(Integer.parseInt(uid)).orElse(null);
    	if(cu2==null) {
    		Utente u = cu.getUtente();
    		u.setRuolo(Utente.Ruolo.ADMIN_ROLE);
    		utenteRepository.save(u);
    		cu.setStato(CircoloUtente.Stato.APPROVATO);
    		emailService.sendEmail(u.getEmail(), "Nuovo circolo creato", "Gentile utente il suo circolo ora è pubblico");
    		return circoloUtenteMapper.convertEntityToDto(circoloUtenteRepository.save(cu));
    	}else {
    		throw new Exception("L'utente ha già un proposta di creazione approvata");
    	}
	}

	public CircoloUtenteDTO rifiutaCreazione(String cid, String uid) throws Exception {
		if(cid==null || isNaN(cid) || uid==null || isNaN(uid)) {
    		throw new Exception("id non validi");
    	}
    	CircoloUtente cu= circoloUtenteRepository.findCreazioneByCircoloAndUtente(Integer.parseInt(cid), Integer.parseInt(uid)).orElse(null);
    	if(cu==null) {
    		throw new Exception("Proposta non trovata");
    	}
    	Utente u = cu.getUtente();
    	cu.setStato(CircoloUtente.Stato.NON_APPROVATO);
    	emailService.sendEmail(u.getEmail(), "Nuovo circolo rifiutato", "Gentile utente il suo circolo non è attivo");
		return circoloUtenteMapper.convertEntityToDto(circoloUtenteRepository.save(cu));
	}

//	public CircoloUtenteDTO inserisci(CircoloUtenteDTO circoloUtenteDTO) throws Exception {
//		if(circoloUtenteDTO == null || circoloUtenteDTO.getIdCircolo()==null || circoloUtenteDTO.getIdUtente()==null) {
//			throw new Exception("Input non valido");
//		}
//		CircoloUtente cu = circoloUtenteRepository.save(circoloUtenteMapper.convertDtoToEntity(circoloUtenteDTO));
//		return this.getById(cu.getCircolo().getId().toString(),cu.getUtente().getId().toString());
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
