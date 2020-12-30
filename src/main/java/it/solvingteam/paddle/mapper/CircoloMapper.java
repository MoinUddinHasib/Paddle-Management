package it.solvingteam.paddle.mapper;

import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.CircoloDTO;
import it.solvingteam.paddle.model.Circolo;

@Component
public class CircoloMapper extends AbstractMapper<Circolo, CircoloDTO> {

	@Override
	public CircoloDTO convertEntityToDto(Circolo entity) {
		if(entity==null) 
			return null;
		
		CircoloDTO circoloDto = new CircoloDTO();
		circoloDto.setId(entity.getId().toString());
		circoloDto.setNome(entity.getNome());
		circoloDto.setCity(entity.getCity());
		circoloDto.setLogo(entity.getLogo());
		return circoloDto;
	}

	@Override
	public Circolo convertDtoToEntity(CircoloDTO dto) throws Exception {
		if(dto==null)
			return null;
		
		Circolo c = new Circolo();
		if(dto.getId()!=null)
			c.setId(Integer.parseInt(dto.getId()));
		
		c.setNome(dto.getNome());
		c.setCity(dto.getCity());
		c.setLogo(dto.getLogo());
		return c;
	}

}
