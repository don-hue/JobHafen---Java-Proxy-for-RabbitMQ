package com.JobHafen.Proxy.service;

import com.JobHafen.Proxy.config.RabbitMQJobConfig;
import com.JobHafen.Proxy.dto.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class JobPublisher {
    private final RabbitTemplate rabbitTemplate;

    public JobPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Message sendJobRequest(Message message) {
        return (Message) rabbitTemplate.convertSendAndReceive(
                RabbitMQJobConfig.EXCHANGE,
                RabbitMQJobConfig.ROUTING_KEY,
                message
        );
    }
}
