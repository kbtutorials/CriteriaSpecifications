package com.filter.demo.service;

import com.filter.demo.model.TimerInfo;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JobsCommonUtils {

    public JobDetail getJobDetails(Class className, TimerInfo timerInfo){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(className.getSimpleName(),timerInfo);

        return JobBuilder
                .newJob(className)
                .withIdentity(className.getSimpleName())
                .setJobData(jobDataMap)
                .build();
    }
    public Trigger setTriggerDetails(Class className, TimerInfo timerInfo){
      SimpleScheduleBuilder  builder = SimpleScheduleBuilder
                .simpleSchedule()
              .withIntervalInMilliseconds(timerInfo.getRepeatInterval());
      if(timerInfo.isRunForever()){
        builder.repeatForever();
      }else{
          builder.withRepeatCount(timerInfo.getTotalFireCount()-1);
      }

        return TriggerBuilder
                .newTrigger()
                .withIdentity(className.getSimpleName())
                .withSchedule(builder)
                .startAt(new Date(System.currentTimeMillis()+timerInfo.getInitialOffSet()))
                .build();
    }



}
