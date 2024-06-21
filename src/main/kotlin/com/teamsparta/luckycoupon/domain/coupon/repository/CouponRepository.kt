package com.teamsparta.luckycoupon.domain.coupon.repository

import com.teamsparta.luckycoupon.domain.coupon.model.Coupon
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface CouponRepository : JpaRepository<Coupon, Long> {
    fun deleteByExpiredAtBefore(now: LocalDateTime)
}
