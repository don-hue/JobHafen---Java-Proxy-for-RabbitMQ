package com.JobHafen.Proxy.service;

import com.JobHafen.Proxy.config.RabbitMQSearchConfig;
import com.JobHafen.Proxy.dto.SearchDto;
import com.JobHafen.Proxy.dto.SearchEntityDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SearchPublisher {
    private final RabbitTemplate rabbitTemplate;
    public SearchPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public SearchEntityDto sendSaveSearchRequest(SearchDto searchDto) {
        return (SearchEntityDto) rabbitTemplate.convertSendAndReceive(
                RabbitMQSearchConfig.EXCHANGE,
                RabbitMQSearchConfig.ROUTING_KEY,
                searchDto
        );
    }
}
