package it.solvingteam.paddle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.paddle.dto.CircoloUtenteDTO;
import it.solvingteam.paddle.dto.ErrorDTO;
import it.solvingteam.paddle.service.CircoloUtenteService;

@RestController
@RequestMapping("proposta")
public class CircoloUtenteController {

    @Autowired
    private CircoloUtenteService circoloUtenteService;
    
    @PutMapping("/iscrizione/disapprova/{idc}/{idu}/{ida}")
    public ResponseEntity<CircoloUtenteDTO> rifiutaPartecipazione(@PathVariable("idu") String idu, @PathVariable("idc") String idc, @PathVariable("ida") String ida) throws Exception {
        return ResponseEntity.ok().body(circoloUtenteService.rifiutaPartecipazione(idc,idu,ida));
    }
    
    @PutMapping("/iscrizione/accetta/{idc}/{idu}/{ida}")
    public ResponseEntity<CircoloUtenteDTO> approvaPartecipazione(@PathVariable("idu") String idu, @PathVariable("idc") String idc, @PathVariable("ida") String ida) throws Exception {
        return ResponseEntity.ok().body(circoloUtenteService.approvaPartecipazione(idc,idu,ida));
    }
    
    @GetMapping("/iscrizione/{idu}")
    public ResponseEntity<List<CircoloUtenteDTO>> getAllProposte(@PathVariable("idu") String idu) throws Exception {
    	List<CircoloUtenteDTO> resultProposteDTO = circoloUtenteService.findAllIscrizioni(idu);
        return ResponseEntity.ok().body(resultProposteDTO);
    }
    
    @PostMapping("/partecipazione/{idc}/{idu}/{l}")
    public ResponseEntity<CircoloUtenteDTO> partecipa(@PathVariable("idc") String idc, @PathVariable("idu") String idu, @PathVariable("l") String l) throws Exception {
    	return ResponseEntity.ok().body(circoloUtenteService.partecipa(idc,idu,l));
    }
    
    @PutMapping("/rifiuta/{idc}/{idu}")
    public ResponseEntity<CircoloUtenteDTO> rifiutaCreazione(@PathVariable("idc") String idc, @PathVariable("idu") String idu) throws Exception {
    	return ResponseEntity.ok().body(circoloUtenteService.rifiutaCreazione(idc,idu));
    }
    
    @PutMapping("/approva/{idc}/{idu}")
    public ResponseEntity<CircoloUtenteDTO> approvaCreazione(@PathVariable("idc") String idc, @PathVariable("idu") String idu) throws Exception {
    	return ResponseEntity.ok().body(circoloUtenteService.approvaCreazione(idc,idu));
    }
    
    @GetMapping("/inLavorazione")
    public ResponseEntity<List<CircoloUtenteDTO>> getAllCreazioniPending() {
    	List<CircoloUtenteDTO> resultProposteDTO = circoloUtenteService.findCreazioniInLavorazione();
        return ResponseEntity.ok().body(resultProposteDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
    }


}
