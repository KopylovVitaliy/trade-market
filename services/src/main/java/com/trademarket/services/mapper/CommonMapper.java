package com.trademarket.services.mapper;

import java.util.List;

/**
 *
 * @param <D> Dto
 * @param <E> Entity
 */
public interface CommonMapper <D, E> {
    E toEntity(D dto);
    D toDto(E entity);

    List<E> toEntityList(List<D> list);
    List<D> toDtoList(List<E> list);
}
