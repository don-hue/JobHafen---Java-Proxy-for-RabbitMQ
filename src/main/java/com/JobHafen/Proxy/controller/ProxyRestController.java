package com.JobHafen.Proxy.controller;

import com.JobHafen.Proxy.dto.Message;
import com.JobHafen.Proxy.dto.SearchDto;
import com.JobHafen.Proxy.dto.SearchEntityDto;
import com.JobHafen.Proxy.service.JobPublisher;
import com.JobHafen.Proxy.service.SearchPublisher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/proxy")
@CrossOrigin(origins = "http://localhost:4200")
public class ProxyRestController {
    private static final Log log = LogFactory.getLog(ProxyRestController.class);
    private final JobPublisher jobPublisher;
    private final SearchPublisher searchPublisher;

    public ProxyRestController(JobPublisher jobPublisher, SearchPublisher searchPublisher) {
        this.jobPublisher = jobPublisher;
        this.searchPublisher = searchPublisher;
    }
    @PostMapping("/sendJobRequest")
    public Message publishMessage(@RequestBody Message message) {
        System.out.println("Message sent");
        return jobPublisher.sendJobRequest(message);
    }
    @PostMapping("/sendSaveSearchRequest")
    public SearchEntityDto publishSaveSearch(@RequestBody SearchDto searchDto) {
        return searchPublisher.sendSaveSearchRequest(searchDto);
    }




}
