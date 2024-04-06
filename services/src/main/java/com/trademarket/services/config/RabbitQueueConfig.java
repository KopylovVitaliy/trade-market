package com.trademarket.services.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitQueueConfig {
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setReplyTimeout(30000);
        return rabbitTemplate;
    }
    @Bean
    public Queue personQueue() {
        return new Queue("persons", true);
    }

    @Bean
    public Queue advertisenentsQueue() {
        return new Queue("advertisements", false);
    }
    @Bean
    public Queue commentsQueue() {
        return new Queue("comments", false);
    }
}
