package com.bieggerm.feedservice.adapters.out.RabbitMQ;

import com.bieggerm.feedservice.app.ports.outgoing.MessageBroker;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQImpl implements MessageBroker {

    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange exchange;

    public RabbitMQImpl(RabbitTemplate rabbitTemplate, TopicExchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    @Override
    public void sendMessage(String message, String topic) {
        rabbitTemplate.convertAndSend(exchange.getName(), topic, message);
    }
}
