package com.trademarket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AdvertisementDto implements Serializable {
    private UUID id;

    private UUID authorId;

    private UUID imageId;

    private String title;

    private String description;

    private int price;

    private List<CommentDto> comments;
}
