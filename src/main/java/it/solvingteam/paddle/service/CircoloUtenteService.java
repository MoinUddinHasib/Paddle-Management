package it.solvingteam.paddle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.paddle.dto.CircoloUtenteDTO;
import it.solvingteam.paddle.mapper.CircoloUtenteMapper;
import it.solvingteam.paddle.model.Circolo;
import it.solvingteam.paddle.model.CircoloUtente;
import it.solvingteam.paddle.model.Utente;
import it.solvingteam.paddle.repository.CircoloRepository;
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
	
	@Autowired
	private CircoloRepository circoloRepository;

	public CircoloUtenteDTO rifiutaPartecipazione(String idc, String idu, String ida) throws Exception {
		if(isNaN(idc) || isNaN(idu) || isNaN(ida)) {
    		throw new Exception("id non validi");
    	}
		CircoloUtente cu= circoloUtenteRepository.findCircoloApprovatoByUtente(Integer.parseInt(ida)).orElse(null);
		if(cu.getCircolo().getId()!=Integer.parseInt(idc)) {
			throw new Exception("Non è un tuo circolo");
		}
		CircoloUtente cu2= circoloUtenteRepository.getIscrizioneByCircoloAndGuest(Integer.parseInt(idc),Integer.parseInt(idu));
		if(cu2==null) {
			throw new Exception("Proposta di iscrizione inesistente");
		}
		Utente u = cu2.getUtente();
		u.setLivello(null);
		utenteRepository.save(u);
		cu2.setStato(CircoloUtente.Stato.NON_APPROVATO);
		return circoloUtenteMapper.convertEntityToDto(circoloUtenteRepository.save(cu2));
	}
	
	public CircoloUtenteDTO approvaPartecipazione(String idc, String idu, String ida) throws Exception {
		if(isNaN(idc) || isNaN(idu) || isNaN(ida)) {
    		throw new Exception("id non validi");
    	}
		CircoloUtente cu= circoloUtenteRepository.findCircoloApprovatoByUtente(Integer.parseInt(ida)).orElse(null);
		if(cu.getCircolo().getId()!=Integer.parseInt(idc)) {
			throw new Exception("Non è un tuo circolo");
		}
		CircoloUtente cu2= circoloUtenteRepository.getIscrizioneByCircoloAndGuest(Integer.parseInt(idc),Integer.parseInt(idu));
		if(cu2==null) {
			throw new Exception("Proposta di iscrizione inesistente");
		}
		Utente u = cu2.getUtente();
		u.setRuolo(Utente.Ruolo.PLAYER_ROLE);
		utenteRepository.save(u);
		cu2.setStato(CircoloUtente.Stato.APPROVATO);
		return circoloUtenteMapper.convertEntityToDto(circoloUtenteRepository.save(cu2));
	}
		
	public List<CircoloUtenteDTO> findAllIscrizioni(String idu) throws Exception {
		if(isNaN(idu)) {
			throw new Exception("id non valido");
		}
		CircoloUtente c = circoloUtenteRepository.findCircoloApprovatoByUtente(Integer.parseInt(idu)).orElse(null);
		if(c==null) {
			throw new Exception("L'admin non ha un circolo");
		}
		List<CircoloUtente> allCircoliUtenti = this.circoloUtenteRepository.findAllIscrizzioniByIdu(c.getCircolo().getId());
        return circoloUtenteMapper.convertEntityToDto(allCircoliUtenti);
	}
	
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
    	CircoloUtente cu2= circoloUtenteRepository.findCircoloApprovatoByUtente(Integer.parseInt(uid)).orElse(null);
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
    	emailService.sendEmail(u.getEmail(), "Nuovo circolo rifiutato", "Gentile utente il suo circolo non è stato approvato");
		return circoloUtenteMapper.convertEntityToDto(circoloUtenteRepository.save(cu));
	}
	
	public CircoloUtenteDTO partecipa(String cid, String uid, String l) throws Exception {
		if(cid==null || isNaN(cid) || uid==null || isNaN(uid) || l==null || isNaN(l)) {
    		throw new Exception("id non validi");
    	}
		Integer livello=Integer.parseInt(l);
		if(livello<1 || livello>7) {
			throw new Exception("Livello non valido");
		}
		Utente u = utenteRepository.getOne(Integer.parseInt(uid));
		Circolo c = circoloRepository.getOne(Integer.parseInt(cid));
		CircoloUtente cu= circoloUtenteRepository.findByCircoloApprovatoById(Integer.parseInt(cid)).orElse(null);
		CircoloUtente uc = circoloUtenteRepository.findIscrizioneByUtente(Integer.parseInt(uid)).orElse(null);
		if(uc!=null) {
			throw new Exception("Hai già presentato un'iscrizione");
		}

		if(c!=null &&  u!=null && cu!=null) {
			CircoloUtente temp=new CircoloUtente();
			u.setLivello(livello);
			temp.setUtente(utenteRepository.save(u));
			temp.setCircolo(c);
			temp.setStato(CircoloUtente.Stato.IN_LAVORAZIONE);
			temp.setTipo(CircoloUtente.Tipo.ISCRIZIONE);
			return circoloUtenteMapper.convertEntityToDto(circoloUtenteRepository.save(temp));
		}else {
			throw new Exception("Il circolo o l'utente non esite");
		}
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
