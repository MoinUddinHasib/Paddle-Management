package it.solvingteam.paddle.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.UtenteDTO;
import it.solvingteam.paddle.model.Utente;

@Component
public class UtenteMapper extends AbstractMapper<Utente, UtenteDTO> {

	@Override
	public UtenteDTO convertEntityToDto(Utente entity) {
		if(entity==null) 
			return null;
		
		UtenteDTO uDto= new UtenteDTO();
		uDto.setId(entity.getId().toString());
		uDto.setNome(entity.getNome());
		uDto.setCognome(entity.getCognome());
		uDto.setDataNascita(entity.getDataNascita().toString());
		uDto.setEmail(entity.getEmail());
		uDto.setCellulare(entity.getCellulare().toString());
		uDto.setRuolo(entity.getRuolo().toString());
		uDto.setFoto(entity.getFoto());
		uDto.setLivello(entity.getLivello().toString());
		uDto.setPassword(entity.getPassword());
		return uDto;
	}

	@Override
	public Utente convertDtoToEntity(UtenteDTO dto) throws Exception {
		if(dto==null)
			return null;
		
		Utente u = new Utente();
		if(dto.getId()!=null)
			u.setId(Integer.parseInt(dto.getId()));
		u.setNome(dto.getNome());
		u.setCognome(dto.getCognome());
		u.setDataNascita(LocalDate.parse(dto.getDataNascita()));
		u.setEmail(dto.getEmail());
		u.setCellulare(Integer.parseInt(dto.getCellulare()));
		u.setRuolo(Utente.Ruolo.valueOf(dto.getRuolo()));
		u.setFoto(dto.getFoto());
		u.setLivello(Integer.parseInt(dto.getLivello()));
		u.setPassword(dto.getPassword());
		return u;
	}

}
