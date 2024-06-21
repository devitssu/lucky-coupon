package com.teamsparta.luckycoupon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class LuckyCouponApplication

fun main(args: Array<String>) {
    runApplication<LuckyCouponApplication>(*args)
}
