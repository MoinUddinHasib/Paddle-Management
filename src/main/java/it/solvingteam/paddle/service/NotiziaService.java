package it.solvingteam.paddle.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.paddle.dto.NotiziaDTO;
import it.solvingteam.paddle.mapper.NotiziaMapper;
import it.solvingteam.paddle.model.CircoloUtente;
import it.solvingteam.paddle.model.Notizia;
import it.solvingteam.paddle.repository.CircoloUtenteRepository;
import it.solvingteam.paddle.repository.NotiziaRepository;

@Service
public class NotiziaService {

	@Autowired
	private NotiziaRepository notiziaRepository;
	
	@Autowired
	private CircoloUtenteRepository circoloUtenteRepository;
	    
	@Autowired
	private NotiziaMapper notiziaMapper;

    private NotiziaDTO getById(String id) throws Exception {
    	if(id==null || isNaN(id)) {
    		throw new Exception("id non valido");
    	}
    	Notizia c= notiziaRepository.findById(Integer.parseInt(id)).orElse(null);
    	if(c==null) {
    		throw new Exception("Notizia non trovata");
    	}
    	return notiziaMapper.convertEntityToDto(c);
    }

	public NotiziaDTO inserisci(NotiziaDTO notiziaDTO, String idu) throws Exception {
		if(notiziaDTO == null || idu==null || isNaN(idu)) {
			throw new Exception("Input non valida");
		}
		CircoloUtente cu = circoloUtenteRepository.findCircoloApprovatoByUtente(Integer.parseInt(idu)).orElse(null);
		if(cu==null) {
			throw new Exception("Non hai un circolo");
		}
		Notizia n = new Notizia();
		n.setMessagio(notiziaDTO.getMessagio());
		n.setData(LocalDate.now());
		n.setCircolo(cu.getCircolo());
		return this.getById(notiziaRepository.save(n).getId().toString());
	}

	public NotiziaDTO aggiorna(NotiziaDTO notiziaDTO, String idu) throws Exception {
		if(notiziaDTO == null || notiziaDTO.getMessagio()==null || notiziaDTO.getMessagio().isEmpty() ||
				notiziaDTO.getId()==null || isNaN(notiziaDTO.getId())) {
			throw new Exception("Input non valida");
		}
		Notizia n=notiziaRepository.getNotiziaByIdnIdu(Integer.parseInt(notiziaDTO.getId()),Integer.parseInt(idu));
		if(n==null) {
			throw new Exception("Notizia inesistente");
		}
		n.setMessagio(notiziaDTO.getMessagio());
		n.setData(LocalDate.parse(notiziaDTO.getData()));
		return this.getById(notiziaRepository.save(n).getId().toString());		
	}
	

	public void delete(String idn, String idu) throws Exception {
		if(isNaN(idn) || isNaN(idu)) {
			throw new Exception("Errore input");
		}
		Notizia n=notiziaRepository.getNotiziaByIdnIdu(Integer.parseInt(idn),Integer.parseInt(idu));
		if(n==null) {
			throw new Exception("Notizia inesistente");
		}
		notiziaRepository.delete(n);
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
