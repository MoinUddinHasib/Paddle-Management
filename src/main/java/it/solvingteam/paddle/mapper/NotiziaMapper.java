package it.solvingteam.paddle.mapper;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.NotiziaDTO;
import it.solvingteam.paddle.model.Notizia;
import it.solvingteam.paddle.repository.CircoloRepository;

@Component
public class NotiziaMapper extends AbstractMapper<Notizia, NotiziaDTO> {
	
	@Autowired
	private CircoloRepository circoloRepository;

	@Override
	public NotiziaDTO convertEntityToDto(Notizia entity) {
		if(entity==null) 
			return null;
		
		NotiziaDTO notiziaDto = new NotiziaDTO();
		notiziaDto.setId(entity.getId().toString());
		notiziaDto.setMessagio(entity.getMessagio());
		notiziaDto.setData(entity.getData().toString());
		notiziaDto.setCircoloId(entity.getCircolo().getId().toString());
		return notiziaDto;
	}

	@Override
	public Notizia convertDtoToEntity(NotiziaDTO dto) throws Exception {
		if(dto==null)
			return null;
		
		Notizia n = new Notizia();
		if(dto.getId()!=null)
			n.setId(Integer.parseInt(dto.getId()));
		n.setMessagio(dto.getMessagio());
		n.setData(LocalDate.parse(dto.getData()));
		n.setCircolo(circoloRepository.getOne(Integer.parseInt(dto.getCircoloId())));
		return n;
	}

}
