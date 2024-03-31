package com.trademarket.model.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RabbitResponse <B> {
    private String errorMessage;
    private B body;
}
