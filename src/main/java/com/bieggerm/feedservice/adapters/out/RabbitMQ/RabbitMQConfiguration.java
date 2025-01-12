package com.bieggerm.feedservice.adapters.out.RabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfiguration {
    @Value("${service.rabbitmq.exchange}")
    private String rabbitMQExchangeName;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(rabbitMQExchangeName);
    }

    @Bean
    public Queue queue() {
        return new Queue("feed.events");
    }
}

