package it.solvingteam.paddle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.paddle.dto.CircoloDTO;
import it.solvingteam.paddle.dto.ErrorDTO;
import it.solvingteam.paddle.service.CircoloService;

@RestController
@RequestMapping("circolo")
public class CircoloController {

    @Autowired
    private CircoloService circoloService; 
    
    @PostMapping("/proponi/{uid}")
    public ResponseEntity<CircoloDTO> create(@RequestBody CircoloDTO circoloDTO, @PathVariable("uid") String uid) throws Exception {
    	return ResponseEntity.ok().body(circoloService.inserisci(circoloDTO,uid));
    }
    
    @GetMapping
    public ResponseEntity<List<CircoloDTO>> getAll() {
    	List<CircoloDTO> resultCircoliDTO = circoloService.findAllCircoliAttivi();
        return ResponseEntity.ok().body(resultCircoliDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
    }


}
