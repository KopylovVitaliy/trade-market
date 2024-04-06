package com.trademarket.services.service;

import com.trademarket.model.dto.AdvertisementDto;
import com.trademarket.model.dto.CommentDto;
import com.trademarket.model.dto.PersonDto;
import com.trademarket.services.entity.Advertisement;
import com.trademarket.services.entity.Comment;
import com.trademarket.services.entity.Person;
import com.trademarket.services.mapper.CommentMapper;
import com.trademarket.services.mapper.PersonMapper;
import com.trademarket.services.repository.AdvertisementRepository;
import com.trademarket.services.repository.CommentRepository;
import com.trademarket.services.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService
        extends AbstractService<CommentRepository, CommentDto, Comment, CommentMapper> {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Override
    public CommentRepository getRepository() {
        return commentRepository;
    }

    @Override
    public CommentMapper getMapper() {
        return commentMapper;
    }
    public CommentDto save(CommentDto dto) {
        Comment e = toEntity(dto);
        if(e.getCreatedAt() == null){
            e.setCreatedAt(LocalDateTime.now());
        }
        return getMapper().toDto(getRepository().save(e));
    }
    @Override
    public Comment toEntity(CommentDto dto) {
        Comment e = super.toEntity(dto);
        e.setAuthor(personRepository.findById(dto.getAuthorId()).get());
        e.setAdvertisement(advertisementRepository.findById(dto.getAdvertisementId()).get());
        return e;
    }
}
