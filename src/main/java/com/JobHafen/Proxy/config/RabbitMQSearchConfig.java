package com.JobHafen.Proxy.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQSearchConfig {
    public static final String EXCHANGE = "search.exchange";
    public static final String SAVE_SEARCH_QUEUE = "search.save.queue";
    public static final String ROUTING_KEY = "search.request";

    @Bean
    public Queue replySearch() {return new Queue(SAVE_SEARCH_QUEUE);}

    @Bean
    public MessageConverter messageConverter() {
        JacksonJsonMessageConverter converter =
                new JacksonJsonMessageConverter();

        DefaultClassMapper classMapper = new DefaultClassMapper();

        Map<String, Class<?>> idClassMapping = new HashMap<>();

        idClassMapping.put(
                "com.JobHafen.PostgreSQLService.dto.Message",
                com.JobHafen.Proxy.dto.Message.class
        );

        classMapper.setIdClassMapping(idClassMapping);

        converter.setClassMapper(classMapper);

        return converter;
    }

    @Bean
    public DirectExchange exchange() {return new DirectExchange(EXCHANGE);}

    @Bean
    public Binding bindung(){
        return BindingBuilder
                .bind(replySearch())
                .to(exchange())
                .with(ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        template.setReplyTimeout(10000);
        return template;
    }
}
