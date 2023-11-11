package com.filter.demo.controller;

import com.filter.demo.schedular.HelloWorldSchedular;
import lombok.AllArgsConstructor;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class HelloWorldJobController {
    private final HelloWorldSchedular helloWorldSchedular;

    @GetMapping("/getJobDetails")
    public List<JobDataMap> getJobDetails(){
       return  helloWorldSchedular.getAllJobDetails();

    }

    @GetMapping("/getSingleJobDetails")
    public JobDetail getSingleJobDetails(@PathVariable String jobName){
        return  helloWorldSchedular.getSingleJob(jobName);

    }




}
