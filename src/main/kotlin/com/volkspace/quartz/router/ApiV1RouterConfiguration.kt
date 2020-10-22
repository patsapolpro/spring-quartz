package com.volkspace.quartz.router

import com.volkspace.quartz.handler.ScheduleHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router

@Configuration
class ApiV1RouterConfiguration {
    @Bean
    fun apiV1Router(
            scheduleHandler: ScheduleHandler
    ): RouterFunction<*> = router {
        "/spring-quartz".nest {
            "/api/v1".nest {
                "/job-scheduler".nest {
                    POST("/create", scheduleHandler::createSchedule)
                }
            }
        }
    }
}