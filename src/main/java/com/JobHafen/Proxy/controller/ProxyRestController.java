package com.JobHafen.Proxy.controller;

import com.JobHafen.Proxy.dto.Job;
import com.JobHafen.Proxy.dto.Message;
import com.JobHafen.Proxy.service.JobPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/proxy")
@CrossOrigin(origins = "http://localhost:4200")
public class ProxyRestController {
    private final JobPublisher jobPublisher;

    public ProxyRestController(JobPublisher jobPublisher) {
        this.jobPublisher = jobPublisher;
    }
    @PostMapping("/sendJobRequest")
    public Job publishMessage(@RequestBody Message message) {
        return jobPublisher.sendJobRequest(message);
    }
}
