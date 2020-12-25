package it.solvingteam.paddle.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMapper<E, D> {

    public abstract D convertEntityToDto(E entity);

    public abstract E convertDtoToEntity(D dto) throws Exception;

    public List<D> convertEntityToDto(List<E> entities) {
        if (entities == null) {
            return null;
        }

        List<D> dtos = new ArrayList<>();

        for (E entity : entities) {
            dtos.add(convertEntityToDto(entity));
        }

        return dtos;
    }

    public List<E> convertDtoToEntity(List<D> dtos) throws Exception {
        if (dtos == null) {
            return null;
        }

        List<E> entities = new ArrayList<>();

        for (D dto : dtos) {
            entities.add(convertDtoToEntity(dto));
        }

        return entities;
    }

}
