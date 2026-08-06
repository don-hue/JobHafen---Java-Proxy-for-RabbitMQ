package com.JobHafen.Proxy.service;

import com.JobHafen.Proxy.config.RabbitMQConfig;
import com.JobHafen.Proxy.dto.Job;
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
        System.out.println("XXX in sendJobRequest");
        return (Message) rabbitTemplate.convertSendAndReceive(
                "jobs.exchange",
                "jobs.request",
                message
        );
    }
}
