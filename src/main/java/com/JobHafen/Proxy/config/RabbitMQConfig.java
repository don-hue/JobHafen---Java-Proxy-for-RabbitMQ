package com.JobHafen.Proxy.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE = "jobs.reply.queue";

    @Bean
    public Queue replyJobs() {
        return new Queue(QUEUE);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("jobs.exchange");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(replyJobs())
                .to(exchange())
                .with("jobs.request");
    }

    @Bean
    public RabbitTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        template.setReplyTimeout(10000);
        return template;
    }
}
