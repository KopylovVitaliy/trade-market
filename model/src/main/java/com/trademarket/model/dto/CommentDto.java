package com.trademarket.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
@NoArgsConstructor
@Data
public class CommentDto implements Serializable {

    private UUID commentId;

    private PersonDto author;

    private AdvertisementDto advertisement;

    private LocalDateTime createdAt;

    private String text;
}
