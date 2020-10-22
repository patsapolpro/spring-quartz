package com.volkspace.quartz.handler

import com.volkspace.quartz.usecase.CreateJobSchedulerUseCase
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.text.SimpleDateFormat
import java.util.*

@Component
class ScheduleHandler(
        val createJobSchedulerUseCase: CreateJobSchedulerUseCase
) {
    fun createSchedule(request: ServerRequest): Mono<ServerResponse> {
        val dateStr: String? = request.queryParam("date").orElse(null)

        val targetDate: Date = convertStringToDate(dateStr)

        createJobSchedulerUseCase.execute(targetDate)
        return ServerResponse.ok().build()
    }

    fun convertStringToDate(dateStr: String?): Date {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return dateFormat.parse(dateStr)
    }
}