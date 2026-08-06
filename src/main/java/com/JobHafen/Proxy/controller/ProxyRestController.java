package com.JobHafen.Proxy.controller;

import com.JobHafen.Proxy.dto.Job;
import com.JobHafen.Proxy.dto.Message;
import com.JobHafen.Proxy.service.JobPublisher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/proxy")
@CrossOrigin(origins = "http://localhost:4200")
public class ProxyRestController {
    private static final Log log = LogFactory.getLog(ProxyRestController.class);
    private final JobPublisher jobPublisher;

    public ProxyRestController(JobPublisher jobPublisher) {
        this.jobPublisher = jobPublisher;
    }
    @PostMapping("/sendJobRequest")
    public Message publishMessage(@RequestBody Message message) {
        System.out.println("Message sent");
        return jobPublisher.sendJobRequest(message);
    }
}
