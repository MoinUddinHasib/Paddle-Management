package it.solvingteam.paddle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.paddle.dto.ErrorDTO;
import it.solvingteam.paddle.dto.PartitaDTO;
import it.solvingteam.paddle.service.PartitaService;

@RestController
@RequestMapping("partita")
public class PartitaController {

    @Autowired
    private PartitaService partitaService;

    @PutMapping("/paga/{idp}/{idu}")
    public ResponseEntity<PartitaDTO> update(@PathVariable("idp") String idp, @PathVariable("idu") String idu) throws Exception {
    	return ResponseEntity.ok().body(partitaService.paga(idp,idu));
    }
//    
//    @PostMapping
//    public ResponseEntity<PartitaDTO> create(@RequestBody PartitaDTO partitaDTO) throws Exception {
//    	return ResponseEntity.ok().body(partitaService.inserisci(partitaDTO));
//    }
//    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<PartitaDTO> delete(@PathVariable("id") String id) throws Exception {
//		partitaService.delete(id);
//		return ResponseEntity.ok().build();
//
//    } 
//       
//    
//    @GetMapping
//    public ResponseEntity<List<PartitaDTO>> getAll() {
//    	List<PartitaDTO> resultComponentiDTO = partitaService.findAll();
//        return ResponseEntity.ok().body(resultComponentiDTO);
//    }
    
  @GetMapping("/{idc}/{data}")
  public ResponseEntity<List<PartitaDTO>> getOne(@PathVariable("idc") String idc, @PathVariable("data") String data) throws Exception {
		return ResponseEntity.ok().body(partitaService.getByIdCampo(idc,data));
  } 

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
    }


}
