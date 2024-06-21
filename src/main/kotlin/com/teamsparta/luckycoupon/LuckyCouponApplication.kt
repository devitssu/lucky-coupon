package com.teamsparta.luckycoupon

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
@SpringBootApplication
class LuckyCouponApplication

fun main(args: Array<String>) {
    runApplication<LuckyCouponApplication>(*args)
}
