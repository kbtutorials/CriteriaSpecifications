package com.filter.demo.schedular;

import com.filter.demo.model.TimerInfo;
import com.filter.demo.service.JobsCommonUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class HelloWorldSchedular {
    private final Scheduler scheduler;
    private final JobsCommonUtils jobsCommonUtils;

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
        }catch (SchedulerException e){
          e.printStackTrace();
        }
    }

    public void scheduleJob(Class className, TimerInfo timerInfo){
        try {
            JobDetail jobDetail = jobsCommonUtils.getJobDetails(className,timerInfo);
            Trigger trigger = jobsCommonUtils.setTriggerDetails(className, timerInfo);
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    @PreDestroy
    public void destroy(){
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }


}
