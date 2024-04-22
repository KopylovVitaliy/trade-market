package com.trademarket.services.mapper;

import com.trademarket.model.dto.CommentDto;
import com.trademarket.services.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CommentMapper implements CommonMapper<CommentDto, Comment> {
    @Override
    public Comment toEntity(CommentDto dto) {
        return Comment.builder()
                .commentId(dto.getCommentId())
                .text(dto.getText())
                .createdAt(dto.getCreatedAt())
                .build();
    }

    @Override
    public CommentDto toDto(Comment entity) {
        return CommentDto.builder()
                .commentId(entity.getCommentId())
                .text(entity.getText())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Override
    public List<Comment> toEntityList(List<CommentDto> list) {
        return list.stream()
                .map(this::toEntity)
                .toList();
    }

    @Override
    public List<CommentDto> toDtoList(List<Comment> list) {
        return list.stream()
                .map(this::toDto)
                .toList();
    }
}
