package com.trademarket.services.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trademarket.model.dto.CommentDto;
import com.trademarket.services.entity.Comment;
import com.trademarket.services.mapper.CommentMapper;
import com.trademarket.services.repository.CommentRepository;
import com.trademarket.services.service.CommentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentsListener extends AbstractListener<CommentDto, CommentRepository,
        Comment, CommentMapper, CommentService> {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected CommentService getService() {
        return commentService;
    }

    @Override
    protected ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @Override
    protected Class<CommentDto> getDtoClass() {
        return CommentDto.class;
    }

    @Override
    @RabbitListener(queues = "comments")
    public String listen(String request) {
        return commonListen(request);
    }
}
