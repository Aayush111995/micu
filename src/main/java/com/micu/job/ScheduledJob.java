package com.micu.job;

import org.quartz.*;

/**
 * Register a cron job
 *
 * @author Aayush
 */
public enum ScheduledJob {

    TESTING {
        @Override
        public Class<? extends MicuJob> className() {
            // testing micu job
            return MicuTestJob.class;
        }

        @Override
        public String getCronTime() {
            // 8 am every day
            return "0 0 8 * * ?";
        }

    };


    public abstract Class<? extends MicuJob> className();

    /**
     * Cron job pattern
     *
     * @return
     */
    public abstract String getCronTime();

    /**
     * {@link JobDetail} about the process
     *
     * @return
     */
    public JobDetail getJobDetail() {
        return JobBuilder.newJob(className()).withIdentity(new JobKey(name(), groupName())).requestRecovery(true)
                .build();
    }

    /**
     * When to trigger this job
     *
     * @return
     */
    public Trigger getJobTrigger() {
        Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity(className().getSimpleName(), groupName())
                .withSchedule(CronScheduleBuilder.cronSchedule(getCronTime())).build();
        return trigger1;
    }

    /**
     * Just to club similar cron jobs which some group.
     *
     * @return
     */
    public String groupName() {
        return "GENERAL";
    }

}
