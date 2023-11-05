package com.filter.demo.service;

import com.filter.demo.jobs.HelloWorldJob;
import com.filter.demo.model.TimerInfo;
import com.filter.demo.schedular.HelloWorldSchedular;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HelloWorldService {
    private final HelloWorldSchedular schedular;

    @PostConstruct
    public void init(){
        TimerInfo timerInfo = new TimerInfo(5,false,2000,1000,null);
        schedular.scheduleJob(HelloWorldJob.class,timerInfo);
    }
}
