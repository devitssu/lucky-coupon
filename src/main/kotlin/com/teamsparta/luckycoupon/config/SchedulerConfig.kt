package com.teamsparta.luckycoupon.config

import net.javacrumbs.shedlock.core.LockProvider
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class SchedulerConfig {
    @Bean
    fun lockProvider(dataSource: DataSource): LockProvider = JdbcTemplateLockProvider(dataSource)
}