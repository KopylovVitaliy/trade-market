package com.trademarket.services.mapper;

import com.trademarket.model.dto.CommentDto;
import com.trademarket.model.dto.PersonDto;
import com.trademarket.services.entity.Comment;
import com.trademarket.services.entity.Person;
import org.mapstruct.Mapper;

@Mapper
public interface CommentMapper extends CommonMapper<CommentDto, Comment> {
}
