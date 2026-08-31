package com.JobHafen.Proxy.producer;

import com.JobHafen.Proxy.config.RabbitMQSearchConfig;
import com.JobHafen.Proxy.dto.SearchDto;
import com.JobHafen.Proxy.dto.SearchEntityDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProducer {
    private final RabbitTemplate rabbitTemplate;
    public SearchProducer(@Qualifier("searchTemplate") RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<SearchEntityDto> publishSaveSearch(SearchDto searchDto) {
        return rabbitTemplate.convertSendAndReceiveAsType(
                RabbitMQSearchConfig.EXCHANGE,
                RabbitMQSearchConfig.ROUTING_KEY,
                searchDto,
                new ParameterizedTypeReference<List<SearchEntityDto>>() {}
        );
    }

    public List<SearchEntityDto> publishGetSearchRequest() {
        return rabbitTemplate.convertSendAndReceiveAsType(
                RabbitMQSearchConfig.EXCHANGE,
                RabbitMQSearchConfig.GET_SEARCH_ROUTING_KEY,
                new Byte[0],
                new ParameterizedTypeReference<List<SearchEntityDto>>() {}
        );
    }

    public ResponseEntity<Void> publishDeleteSearch(Long searchId){
        return rabbitTemplate.convertSendAndReceiveAsType(
                RabbitMQSearchConfig.EXCHANGE,
                RabbitMQSearchConfig.DELETE_SEARCH_ROUTING_KEY,
                searchId,
                new ParameterizedTypeReference<ResponseEntity<Void>>() {}
        );
    }
}
