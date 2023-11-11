package com.filter.demo.service;

import com.filter.demo.jobs.DisplaySomethingJob;
import com.filter.demo.model.TimerInfo;
import com.filter.demo.schedular.HelloWorldSchedular;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DisPlayNameSchedule {
    private final HelloWorldSchedular schedular;

    @PostConstruct
    public void init(){
        TimerInfo timerInfo = new TimerInfo(10,false,2000,1000,"secpd");

        schedular.scheduleJob(DisplaySomethingJob.class,timerInfo);
    }
}
