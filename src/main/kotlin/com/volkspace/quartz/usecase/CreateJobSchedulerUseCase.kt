package com.volkspace.quartz.usecase

import com.volkspace.quartz.job.PrintTimeJob
import org.quartz.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreateJobSchedulerUseCase (
        val scheduler: Scheduler
){
    private val log = LoggerFactory.getLogger(this.javaClass)

    fun execute(broadcastTime: Date) {
        val jobDetail: JobDetail = createJobDetail()
        val trigger: Trigger = createTrigger(jobDetail, broadcastTime)
        scheduler.scheduleJob(jobDetail, trigger)
        log.info("Set Job Scheduler Time: $broadcastTime")
    }

    private fun createJobDetail(): JobDetail {
        return JobBuilder.newJob(PrintTimeJob::class.java)
                .withIdentity(UUID.randomUUID().toString(), "group-job")
                .withDescription("Create printTime job")
                .build()
    }

    private fun createTrigger(jobDetail: JobDetail, targetDate: Date): Trigger {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(UUID.randomUUID().toString(), "group")
                .withDescription("Description for trigger")
                .startAt(targetDate)
                .build()
    }
}