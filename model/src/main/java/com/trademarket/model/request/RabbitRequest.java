package com.trademarket.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RabbitRequest {
    private String typeOperation;
    private String body;
}
