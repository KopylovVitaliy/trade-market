package com.trademarket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommentDto implements Serializable {

    private UUID commentId;

    private UUID authorId;

    private UUID advertisementId;

    private LocalDateTime createdAt;

    private String text;
}
