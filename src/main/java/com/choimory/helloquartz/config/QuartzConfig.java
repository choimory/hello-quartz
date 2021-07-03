package com.choimory.helloquartz.config;

import com.choimory.helloquartz.scheduler.LogScheduler;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.util.Date;

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
        trigger.setCronExpression("*/10 * * * * ? *"); //10초마다
        return trigger;
    }

    //SimpleTriggerFactoryBean
    //@Bean
    //public SimpleTriggerFactoryBean jobTrigger(){
    //    SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
    //    simpleTriggerFactoryBean.setJobDetail(quartzJob());
    //    simpleTriggerFactoryBean.setStartTime(new Date()); // 시작일자
    //    simpleTriggerFactoryBean.setRepeatInterval(10000L); // 10초마다 (milli sec)
    //    return simpleTriggerFactoryBean;
    //}

    //TriggerBuilder, Simple Schedule Builder 사용
    //@Bean
    //public Trigger jobTrigger(){
    //    return TriggerBuilder.newTrigger()
    //            .forJob(quartzJob())
    //            .withSchedule(SimpleScheduleBuilder
    //                    .simpleSchedule()
    //                    .withIntervalInSeconds(10) //실행시간으로부터 10초마다
    //                    .repeatForever())
    //            .build();
    //}
}
