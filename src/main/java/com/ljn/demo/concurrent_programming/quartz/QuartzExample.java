package com.ljn.demo.concurrent_programming.quartz;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

public class QuartzExample {
    @Autowired
    private Scheduler scheduler;

    public void deleteJob() {
        try {
            boolean isDelete = scheduler.deleteJob(new JobKey("alphaJob", "alphaJobGroup"));
            System.out.println(isDelete);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
