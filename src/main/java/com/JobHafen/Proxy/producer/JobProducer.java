package com.JobHafen.Proxy.producer;

import com.JobHafen.Proxy.config.RabbitMQJobConfig;
import com.JobHafen.Proxy.dto.JobEntityDto;
import com.JobHafen.Proxy.dto.JobUpdateAppliedDto;
import com.JobHafen.Proxy.dto.SearchToCrawlDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobProducer {
    private final RabbitTemplate rabbitTemplate;

    public JobProducer(@Qualifier("jobTemplate") RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<JobEntityDto> getJobsRequest() {
         List<SearchToCrawlDto> searches = rabbitTemplate.convertSendAndReceiveAsType(
                RabbitMQJobConfig.EXCHANGE,
                RabbitMQJobConfig.GET_SEARCHES_TO_CRAWL_ROUTING_KEY,
                new Byte[0],
                new ParameterizedTypeReference<List<SearchToCrawlDto>>() {}
        );
       if(searches == null) {
           System.out.println("Searches is null");
           throw new RuntimeException("Error, Please try later");
       }
       Boolean isCrawled = rabbitTemplate.convertSendAndReceiveAsType(
               RabbitMQJobConfig.EXCHANGE,
               RabbitMQJobConfig.CRAWL_SEARCHES_ROUTING_KEY,
               searches,
               new ParameterizedTypeReference<Boolean>() {}
       );

       if(isCrawled == null || !isCrawled ) {
           System.out.println("isCrawled is null or false");
           throw new RuntimeException("Error, Please try later");
       }

       return rabbitTemplate.convertSendAndReceiveAsType(
               RabbitMQJobConfig.EXCHANGE,
               RabbitMQJobConfig.GET_ALL_JOBS_ROUTING_KEY,
               new Byte[0],
               new ParameterizedTypeReference<List<JobEntityDto>>(){}
       );

    }

    public ResponseEntity<Void> updateJobIsApplied(JobUpdateAppliedDto jobUpdateAppliedDto) {
        return rabbitTemplate.convertSendAndReceiveAsType(
                RabbitMQJobConfig.EXCHANGE,
                RabbitMQJobConfig.PUT_JOB_APPLIED_JOBS_ROUTING_KEY,
                jobUpdateAppliedDto,
                new ParameterizedTypeReference<ResponseEntity<Void>>() {});
    }
}
