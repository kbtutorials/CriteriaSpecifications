package com.filter.demo.schedular;

import com.filter.demo.model.TimerInfo;
import com.filter.demo.service.JobsCommonUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public List<JobDataMap> getAllJobDetails(){
        List<JobDataMap> jobName = new ArrayList<>();
        try {
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupStartsWith("First"));
            for(JobKey jk: jobKeys){
                JobDetail jobDetail = scheduler.getJobDetail(jk);
                JobDataMap jobDataMap = jobDetail.getJobDataMap();
                jobName.add(jobDataMap);

            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return jobName;
    }

    public JobDetail getSingleJob(String jobName){
        try {
            JobDetail jobDetail = scheduler.getJobDetail(new JobKey(jobName));
            return jobDetail;
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
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
