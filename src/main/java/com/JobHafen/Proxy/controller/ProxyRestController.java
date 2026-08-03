package com.JobHafen.Proxy.controller;

import com.JobHafen.Proxy.Message;
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
    public ResponseEntity<String> publishMessage(@RequestBody Message message) {
        jobPublisher.sendJob("Jobs requested");
        return ResponseEntity.ok("Jobs requested");

    }
}
