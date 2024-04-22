package com.trademarket.model.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RabbitResponse {
    private String errorMessage;
    private String body;

    public RabbitResponse(String body){
        this.body = body;
    }
}
