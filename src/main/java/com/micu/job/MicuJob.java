package com.micu.job;


import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.Serializable;

public abstract class MicuJob implements Job, Serializable {
    private String jobKeyName;
    private String jobKeyGroupName;

    public abstract void execute(JobDataMap jobDataMap);

    public String getJobKeyName() {
        return jobKeyName;
    }

    public void setJobKeyName(String jobKeyName) {
        this.jobKeyName = jobKeyName;
    }

    public String getJobKeyGroupName() {
        return jobKeyGroupName;
    }

    public void setJobKeyGroupName(String jobKeyGroupName) {
        this.jobKeyGroupName = jobKeyGroupName;
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        setJobKeyName(context.getJobDetail().getKey().getName());
        setJobKeyGroupName(context.getJobDetail().getKey().getGroup());
        execute(jobDataMap);
    }

    public void beforeExecute() {
    }

    public void afterExecute() {

    }
}

