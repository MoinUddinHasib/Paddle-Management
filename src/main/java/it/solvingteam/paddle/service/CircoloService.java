package it.solvingteam.paddle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.paddle.dto.CircoloDTO;
import it.solvingteam.paddle.mapper.CircoloMapper;
import it.solvingteam.paddle.model.Circolo;
import it.solvingteam.paddle.model.CircoloUtente;
import it.solvingteam.paddle.model.Utente;
import it.solvingteam.paddle.repository.CircoloRepository;
import it.solvingteam.paddle.repository.CircoloUtenteRepository;
import it.solvingteam.paddle.repository.UtenteRepository;

@Service
public class CircoloService {

	@Autowired
	private CircoloRepository circoloRepository;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private CircoloUtenteRepository circoloUtenteRepository;
	    
	@Autowired
	private CircoloMapper circoloMapper;

	public List<CircoloDTO> findAllCircoliAttivi() {
		List<Circolo> allCircoli = this.circoloRepository.findAllCircoliApprovati();
        return circoloMapper.convertEntityToDto(allCircoli);
	}

	public CircoloDTO inserisci(CircoloDTO circoloDTO, String uid) throws Exception {
		if(uid==null || isNaN(uid) ||circoloDTO==null || circoloDTO.getNome()==null || circoloDTO.getNome().isEmpty() ||
				circoloDTO.getCity()==null || circoloDTO.getCity().isEmpty() || circoloDTO.getLogo()==null) {//TODO logo
			throw new Exception("Input non valido");
		}
		CircoloUtente cu = circoloUtenteRepository.findCircoloNotRifiutatoByUtente(Integer.parseInt(uid)).orElse(null);
		if(cu!=null) {
			throw new Exception("Hai già una proposta di un circolo");
		}
		Utente u = utenteRepository.getOne(Integer.parseInt(uid));
		if(u==null) {
			throw new Exception("Utente inesistente");
		}
		Circolo c= circoloRepository.save(circoloMapper.convertDtoToEntity(circoloDTO));
		CircoloUtente cuRis=new CircoloUtente();
		cuRis.setUtente(u);
		cuRis.setCircolo(c);
		cuRis.setStato(CircoloUtente.Stato.IN_LAVORAZIONE);
		cuRis.setTipo(CircoloUtente.Tipo.CREAZIONE);
		circoloUtenteRepository.save(cuRis);
		return this.getById(c.getId().toString());
	}
	
	private CircoloDTO getById(String id){
    	return circoloMapper.convertEntityToDto(circoloRepository.findById(Integer.parseInt(id)).orElse(null));
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
