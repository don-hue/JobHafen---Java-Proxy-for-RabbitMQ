package com.JobHafen.Proxy.controller;

import com.JobHafen.Proxy.dto.*;
import com.JobHafen.Proxy.producer.JobProducer;
import com.JobHafen.Proxy.producer.SearchProducer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/proxy")
@CrossOrigin(origins = "http://localhost:4200")
public class ProxyRestController {
    private static final Log log = LogFactory.getLog(ProxyRestController.class);
    private final JobProducer jobProducer;
    private final SearchProducer searchProducer;

    public ProxyRestController(JobProducer jobProducer, SearchProducer searchProducer) {
        this.jobProducer = jobProducer;
        this.searchProducer = searchProducer;
    }
    @GetMapping("/getJobsRequest")
    public List<JobEntityDto> publishGetJobs() {
        return jobProducer.getJobsRequest();
    }
    @PostMapping("/sendSaveSearchRequest")
    public List<SearchEntityDto> publishSaveSearch(@RequestBody SearchDto searchDto) {
        SearchDto javaSearchDto = new SearchDto(searchDto.keyword(), searchDto.postal_code(), searchDto.radius());
        return searchProducer.publishSaveSearch(javaSearchDto);
    }

    @GetMapping("/sendGetAllRequest")
    public List<SearchEntityDto> publishGetSearch() {
        return searchProducer.sendGetSearchRequest();
    }
}
