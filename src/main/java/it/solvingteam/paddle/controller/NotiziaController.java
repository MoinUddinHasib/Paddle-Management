package it.solvingteam.paddle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.paddle.dto.ErrorDTO;
import it.solvingteam.paddle.dto.NotiziaDTO;
import it.solvingteam.paddle.service.NotiziaService;

@RestController
@RequestMapping("notizia")
public class NotiziaController {

    @Autowired
    private NotiziaService notiziaService;
    
    @DeleteMapping("/{idn}/{idu}")
    public ResponseEntity<NotiziaDTO> delete(@PathVariable("idn") String idn, @PathVariable("idu") String idu) throws Exception {
    	notiziaService.delete(idn,idu);
		return ResponseEntity.ok().build();
    } 

    @PutMapping("/{idu}")
    public ResponseEntity<NotiziaDTO> update(@RequestBody NotiziaDTO notiziaDTO, @PathVariable("idu") String idu) throws Exception {
    	return ResponseEntity.ok().body(notiziaService.aggiorna(notiziaDTO,idu));
    }
    
    @PostMapping("/{idu}")
    public ResponseEntity<NotiziaDTO> create(@RequestBody NotiziaDTO notiziaDTO, @PathVariable("idu") String idu) throws Exception {
    	return ResponseEntity.ok().body(notiziaService.inserisci(notiziaDTO,idu));
    }
    
//    @GetMapping("/{id}")
//    public ResponseEntity<NotiziaDTO> getOne(@PathVariable("id") String id) throws Exception {
//		return ResponseEntity.ok().body(notiziaService.getById(id));
//    }    
//    
//    @GetMapping
//    public ResponseEntity<List<NotiziaDTO>> getAll() {
//    	List<NotiziaDTO> resultComponentiDTO = notiziaService.findAll();
//        return ResponseEntity.ok().body(resultComponentiDTO);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
    }


}
