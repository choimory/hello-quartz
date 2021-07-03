package com.choimory.helloquartz.config;

import com.choimory.helloquartz.scheduler.LogScheduler;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail quartzJob(){
        return JobBuilder.newJob(LogScheduler.class)
                .withIdentity("logScheduler")
                .withDescription("hello quartz")
                .storeDurably()
                .build();
    }


    //Cron Trigger Factory 사용
    @Bean
    public CronTriggerFactoryBean jobTrigger(){
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(quartzJob());
        trigger.setCronExpression("*/10 * * * * ? *"); //10초 단위로
        return trigger;
    }

    //TriggerBuilder, Simple Schedule Builder 사용
    /*@Bean
    public Trigger jobTrigger(){
        return TriggerBuilder.newTrigger()
                .forJob(quartzJob())
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(10) //실행시간으로부터 10초에 한번씩
                        .repeatForever())
                .build();
    }*/

}
