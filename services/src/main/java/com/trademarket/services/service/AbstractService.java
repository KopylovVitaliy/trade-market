package com.trademarket.services.service;

import com.trademarket.services.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @param <Repository> репозиторий
 * @param <D>          Dto
 * @param <E>          Entity
 * @param <M>          Mapper
 */
public abstract class AbstractService<Repository extends JpaRepository<
        E, UUID>, D, E, M extends CommonMapper<D, E>> {
    Repository repository;
    M mapper;

    public abstract Repository getRepository();

    public abstract M getMapper();


    public D save(D dto) {
        E e = getMapper().toEntity(dto);
        return getMapper().toDto(getRepository().save(e));
    }

    public D getById(UUID id) {
        Optional<E> e = getRepository().findById(id);
        return getMapper().toDto(e.get());
    }

    public List<D> getAll() {
        return getMapper().toDtoList(getRepository().findAll());
    }
}