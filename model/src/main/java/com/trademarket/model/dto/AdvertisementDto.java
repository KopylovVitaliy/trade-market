package com.trademarket.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class AdvertisementDto implements Serializable {
    private UUID id;

    private PersonDto author;

    private ImageDto image;

    private String title;

    private String description;

    private int price;

    private List<CommentDto> comments = new ArrayList<>();
}
