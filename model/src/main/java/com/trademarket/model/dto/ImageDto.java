package com.trademarket.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@Data
public class ImageDto implements Serializable {
    private UUID id;
}
