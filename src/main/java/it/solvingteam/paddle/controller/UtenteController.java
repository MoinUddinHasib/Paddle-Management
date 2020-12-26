package it.solvingteam.paddle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.paddle.dto.ErrorDTO;
import it.solvingteam.paddle.dto.UtenteDTO;
import it.solvingteam.paddle.service.UtenteService;

@RestController
@RequestMapping("utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @PostMapping
    public ResponseEntity<UtenteDTO> signup(@RequestBody UtenteDTO utenteDTO) throws Exception {
    	return ResponseEntity.ok().body(utenteService.inserisci(utenteDTO));
    } 
    
    @PostMapping("/login")
    public ResponseEntity<UtenteDTO> login(@RequestBody UtenteDTO utenteDTO) throws Exception {
    	return ResponseEntity.ok().body(utenteService.login(utenteDTO));
    }  

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
    }


}
