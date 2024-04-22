package com.trademarket.web.controller;

import com.trademarket.model.dto.CommentDto;
import com.trademarket.model.dto.PersonDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController extends AbstractRestController<CommentDto> {
    public CommentRestController(){
        this.ROUTING_KEY = "comments";
    }
}
