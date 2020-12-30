package it.solvingteam.paddle.mapper;

import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.SlotDTO;
import it.solvingteam.paddle.model.Slot;

@Component
public class SlotMapper extends AbstractMapper<Slot, SlotDTO> {

	@Override
	public SlotDTO convertEntityToDto(Slot entity) {
		if(entity==null) 
			return null;
		
		SlotDTO sDto= new SlotDTO();
		sDto.setId(entity.getId().toString());
		sDto.setOra(entity.getOra().toString());
		sDto.setMinuti(entity.getMinuti().toString());
		return sDto;
	}

	@Override
	public Slot convertDtoToEntity(SlotDTO dto) throws Exception {
		if(dto==null)
			return null;
		
		Slot s = new Slot();
		if(dto.getId()!=null)
			s.setId(Integer.parseInt(dto.getId()));
		s.setOra(Integer.parseInt(dto.getOra()));
		s.setMinuti(Integer.parseInt(dto.getMinuti()));
		return s;
	}

}
