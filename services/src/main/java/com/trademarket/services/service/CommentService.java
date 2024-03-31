package com.trademarket.services.service;

import com.trademarket.model.dto.CommentDto;
import com.trademarket.model.dto.PersonDto;
import com.trademarket.services.entity.Comment;
import com.trademarket.services.entity.Person;
import com.trademarket.services.mapper.CommentMapper;
import com.trademarket.services.mapper.PersonMapper;
import com.trademarket.services.repository.CommentRepository;
import com.trademarket.services.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService
        extends AbstractService<CommentRepository, CommentDto, Comment, CommentMapper> {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public CommentRepository getRepository() {
        return commentRepository;
    }

    @Override
    public CommentMapper getMapper() {
        return commentMapper;
    }
}
