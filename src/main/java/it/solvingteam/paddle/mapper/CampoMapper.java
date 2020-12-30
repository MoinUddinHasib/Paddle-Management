package it.solvingteam.paddle.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.CampoDTO;
import it.solvingteam.paddle.model.Campo;
import it.solvingteam.paddle.repository.CircoloRepository;

@Component
public class CampoMapper extends AbstractMapper<Campo, CampoDTO> {
	
	@Autowired
	private CircoloRepository circoloRepository;

	@Override
	public CampoDTO convertEntityToDto(Campo entity) {
		if(entity==null) 
			return null;
		
		CampoDTO campoDto = new CampoDTO();
		campoDto.setId(entity.getId().toString());
		campoDto.setNome(entity.getNome());
		campoDto.setCircoloId(entity.getCircolo().getId().toString());
		campoDto.setAperto(entity.getAperto().toString());
		return campoDto;
	}

	@Override
	public Campo convertDtoToEntity(CampoDTO dto) throws Exception {
		if(dto==null)
			return null;
		
		Campo c = new Campo();
		if(dto.getId()!=null)
			c.setId(Integer.parseInt(dto.getId()));
		
		c.setNome(dto.getNome());
		c.setCircolo(circoloRepository.getOne(Integer.parseInt(dto.getCircoloId())));
		c.setAperto(Boolean.parseBoolean(dto.getAperto()));
		return c;
	}

}
