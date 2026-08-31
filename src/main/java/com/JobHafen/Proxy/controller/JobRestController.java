package com.JobHafen.Proxy.controller;

import com.JobHafen.Proxy.dto.JobEntityDto;
import com.JobHafen.Proxy.dto.JobUpdateAppliedDto;
import com.JobHafen.Proxy.producer.JobProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("proxy/job")
@CrossOrigin(origins="http://localhost:4200")
public class JobRestController {
    private final JobProducer jobProducer;

    public JobRestController (JobProducer jobProducer) {
        this.jobProducer = jobProducer;
    }

    @GetMapping("/getJobsRequest")
    public List<JobEntityDto> publishGetJobs() {
        return jobProducer.getJobsRequest();
    }

    @PutMapping("/updateJobAppliedRequest")
    public ResponseEntity<Void> publishUpdateJob(JobUpdateAppliedDto jobUpdateAppliedDto) {
        return jobProducer.updateJobIsApplied(jobUpdateAppliedDto);
    }
}
