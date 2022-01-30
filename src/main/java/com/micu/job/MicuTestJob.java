package com.micu.job;

import org.quartz.JobDataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MicuTestJob extends MicuJob {


    protected static final Logger log = LoggerFactory.getLogger(MicuTestJob.class);


    @Override
    public void execute(JobDataMap jobDataMap) {
        log.info("executing the job " + jobDataMap.get("NB_REMOTE_JOB_KEY_NAME"));

    }
}
