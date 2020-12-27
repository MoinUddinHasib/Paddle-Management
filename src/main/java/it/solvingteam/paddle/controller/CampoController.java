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

import it.solvingteam.paddle.dto.CampoDTO;
import it.solvingteam.paddle.dto.ErrorDTO;
import it.solvingteam.paddle.service.CampoService;

@RestController
@RequestMapping("campo")
public class CampoController {

    @Autowired
    private CampoService campoService;

//    @PutMapping
//    public ResponseEntity<CampoDTO> update(@RequestBody CampoDTO campoDTO) throws Exception {
//    	return ResponseEntity.ok().body(campoService.aggiorna(campoDTO));
//    }
//    
//    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<CampoDTO> delete(@PathVariable("id") String id) throws Exception {
//		campoService.delete(id);
//		return ResponseEntity.ok().build();
//
//    } 
    
	@PostMapping("/{idu}")
	public ResponseEntity<CampoDTO> create(@RequestBody CampoDTO campoDTO, @PathVariable("idu") String idu) throws Exception {
		return ResponseEntity.ok().body(campoService.inserisci(campoDTO,idu));
	}
    
    @GetMapping("/{idu}")
    public ResponseEntity<List<CampoDTO>> getOne(@PathVariable("idu") String idu) throws Exception {
		return ResponseEntity.ok().body(campoService.getAllMyCampi(idu));
    } 

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
    }


}
