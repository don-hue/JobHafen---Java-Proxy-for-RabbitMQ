package com.JobHafen.Proxy.service;

import com.JobHafen.Proxy.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class JobPublisher {
    private final RabbitTemplate rabbitTemplate;

    public JobPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJob(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE, message);
    }
}
