package it.solvingteam.paddle.mapper;

import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.SlotDTO;
import it.solvingteam.paddle.model.Slot;

@Component
public class SlotMapper extends AbstractMapper<Slot, SlotDTO> {

	@Override
	public SlotDTO convertEntityToDto(Slot entity) {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

	@Override
	public Slot convertDtoToEntity(SlotDTO dto) throws Exception {
		System.err.println("Errore: "+this);
		System.exit(1);
		return null;
	}

}
