package com.JobHafen.Proxy.config;

import com.JobHafen.Proxy.dto.ResponseDto;
import com.JobHafen.Proxy.dto.search.SearchEntityDto;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQSearchConfig {
    public static final String EXCHANGE = "search.exchange";
    public static final String SAVE_SEARCH_QUEUE = "search.save.queue";
    public static final String ROUTING_KEY = "search.request";

    public static final String GET_SEARCH_QUEUE = "search.get.queue";
    public static final String GET_SEARCH_ROUTING_KEY = "search.get.request";

    public static final String DELETE_SEARCH_QUEUE = "search.delete.queue";
    public static final String DELETE_SEARCH_ROUTING_KEY = "search.delete.request";

    @Bean
    public Queue saveSearchQueue() {return new Queue(SAVE_SEARCH_QUEUE);}
    @Bean
    public Queue getSearchQueue() {return new Queue(GET_SEARCH_QUEUE);}

    @Bean
    public Queue deleteSearchQueue(){return new Queue(DELETE_SEARCH_QUEUE);}

    @Bean
    public MessageConverter searchMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public DirectExchange searchExchange() {return new DirectExchange(EXCHANGE);}

    @Bean
    public Binding saveSearchBinding(
            @Qualifier("saveSearchQueue") Queue queue,
            DirectExchange searchExchange) {

        return BindingBuilder
                .bind(queue)
                .to(searchExchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public Binding getSearchBinding(
            @Qualifier("getSearchQueue") Queue queue,
            DirectExchange searchExchange) {

        return BindingBuilder
                .bind(queue)
                .to(searchExchange)
                .with(GET_SEARCH_ROUTING_KEY);
    }
    @Bean
    public Binding deleteSearchBinding(
            @Qualifier("deleteSearchQueue") Queue queue,
            DirectExchange searchExchange) {
        return BindingBuilder
                .bind(queue)
                .to(searchExchange)
                .with(DELETE_SEARCH_ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate searchTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(searchMessageConverter());
        template.setReplyTimeout(30000);
        return template;
    }
}
