package it.solvingteam.paddle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PutMapping
    public ResponseEntity<CircoloUtenteDTO> update(@RequestBody CircoloUtenteDTO circoloUtenteDTO) throws Exception {
    	return ResponseEntity.ok().body(circoloUtenteService.aggiorna(circoloUtenteDTO));
    }
    
    @PostMapping
    public ResponseEntity<CircoloUtenteDTO> create(@RequestBody CircoloUtenteDTO circoloUtenteDTO) throws Exception {
    	return ResponseEntity.ok().body(circoloUtenteService.inserisci(circoloUtenteDTO));
    }
    
    @DeleteMapping("/{idc}/{idu}")
    public ResponseEntity<CircoloUtenteDTO> delete(@PathVariable("idc") String idc, @PathVariable("idu") String idu) throws Exception {
		circoloUtenteService.delete(idc,idu);
		return ResponseEntity.ok().build();

    } 
    
    @GetMapping("/{idc}/{idu}")
    public ResponseEntity<CircoloUtenteDTO> getOne(@PathVariable("idc") String idc, @PathVariable("idu") String idu) throws Exception {
		return ResponseEntity.ok().body(circoloUtenteService.getById(idc,idu));
    }    
    
    @GetMapping("/inLavorazione")
    public ResponseEntity<List<CircoloUtenteDTO>> getAllPending() {
    	List<CircoloUtenteDTO> resultProposteDTO = circoloUtenteService.findAllInLavorazione();
        return ResponseEntity.ok().body(resultProposteDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<CircoloUtenteDTO>> getAll() {
    	List<CircoloUtenteDTO> resultProposteDTO = circoloUtenteService.findAll();
        return ResponseEntity.ok().body(resultProposteDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
    }


}
