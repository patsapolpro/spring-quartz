package com.volkspace.quartz.job

import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.springframework.scheduling.quartz.QuartzJobBean
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class PrintTimeJob : QuartzJobBean() {
    private val log = LoggerFactory.getLogger(this.javaClass)

    var df = SimpleDateFormat("HH:mm:ss")
    override fun executeInternal(context: JobExecutionContext){
        log.info("Executing Job with key: "+ context.jobDetail.key)

        log.info("Time is " + df.format(Date()))
    }
}