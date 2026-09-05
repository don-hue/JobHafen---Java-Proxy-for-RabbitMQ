package com.JobHafen.Proxy.controller;

import de.TheDonJuan.dto.ResponseDto;
import de.TheDonJuan.dto.job.JobEntityDto;
import de.TheDonJuan.dto.job.JobUpdateAppliedDto;
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
        try{
            return jobProducer.getJobsRequest();
        } catch (Exception e) {
            System.out.println("Error in JobRestcontroller" + e.getMessage());
            throw e;
        }
    }

    @PutMapping("/updateJobAppliedRequest")
    public ResponseEntity<Void> publishUpdateJob(JobUpdateAppliedDto jobUpdateAppliedDto) {
        ResponseDto response = jobProducer.updateJobIsApplied(jobUpdateAppliedDto);
        if(response.processed()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
}
