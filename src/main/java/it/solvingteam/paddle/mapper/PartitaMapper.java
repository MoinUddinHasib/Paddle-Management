package it.solvingteam.paddle.mapper;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.paddle.dto.PartitaDTO;
import it.solvingteam.paddle.model.Partita;
import it.solvingteam.paddle.repository.CampoRepository;

@Component
public class PartitaMapper extends AbstractMapper<Partita, PartitaDTO>  {
	
	@Autowired
	private CampoRepository campoRepository;

	@Override
	public PartitaDTO convertEntityToDto(Partita entity) {
		if(entity==null) 
			return null;
		
		PartitaDTO pDto= new PartitaDTO();
		pDto.setId(entity.getId().toString());
		pDto.setData(entity.getData().toString());
		pDto.setStatoPagamento(entity.getStatoPagamento().toString());
		pDto.setGiocatoriMancanti(entity.getGiocatoriMancanti().toString());
		pDto.setCampoId(entity.getCampo().getId().toString());
		return pDto;
	}

	@Override
	public Partita convertDtoToEntity(PartitaDTO dto) throws Exception {
		if(dto==null)
			return null;
		
		Partita p = new Partita();
		if(dto.getId()!=null)
			p.setId(Integer.parseInt(dto.getId()));
		p.setData(LocalDate.parse(dto.getData()));
		p.setStatoPagamento(Boolean.parseBoolean(dto.getStatoPagamento()));
		p.setGiocatoriMancanti(Integer.parseInt(dto.getGiocatoriMancanti()));
		p.setCampo(campoRepository.getOne(Integer.parseInt(dto.getCampoId())));
		return p;
	}

}
