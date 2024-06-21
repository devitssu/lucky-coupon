package com.teamsparta.luckycoupon.domain.coupon.service

import com.teamsparta.luckycoupon.domain.coupon.model.Coupon
import com.teamsparta.luckycoupon.domain.coupon.repository.CouponRepository
import com.teamsparta.luckycoupon.domain.member.model.Member
import com.teamsparta.luckycoupon.domain.member.repository.MemberRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import kotlin.random.Random

private val logger = KotlinLogging.logger {}

@Service
class CouponService(
    private val couponRepository: CouponRepository,
    private val memberRepository: MemberRepository
) {

    //scheduler 사용하여 1시간마다 랜덤한 사용자에게 쿠폰 발급
    //할인율, 만료시간 랜덤
    // @Scheduled(cron = "0 0 0/1 * * *") // 1시간마다
    @Scheduled(cron = "0 0/1 * * * *") // 1분마다 - test용
    @SchedulerLock(name = "CouponScheduler_issueCoupon", lockAtLeastFor = "PT10S", lockAtMostFor = "PT20S")
    @Transactional
    fun issueCoupon() {
        val memberTotalCount = memberRepository.count()
        couponRepository.save(generateCoupon(memberTotalCount))
        logger.info { "issueCoupon() executed." }
    }

    //scheduler 사용하여 자정마다 유효기간이 지난 쿠폰 삭제
    // @Scheduled(cron = "0 0 0 1/1 * *") // 매일 자정마다
    @Scheduled(cron = "0 0/1 * * * *") // 1분마다 - test용
    @SchedulerLock(name = "CouponScheduler_deleteExpiredCoupon", lockAtLeastFor = "PT10S", lockAtMostFor = "PT20S")
    @Transactional
    fun deleteExpiredCoupon() {
        couponRepository.deleteByExpiredAtBefore(LocalDateTime.now())
        logger.info { "deleteExpiredCoupon() executed" }
    }

    private fun generateCoupon(count: Long): Coupon {
        val now = LocalDateTime.now()

        return Coupon(
            discountRate = Random.nextInt(1, 20) * 5, //TODO weighted random 적용해보고싶다.
            createdAt = now,
            // expiredAt = now.plusMinutes(Random.nextLong(1, 6 * 24 * 3) * 10), // 10분 ~ 3일, 10분 간격
            expiredAt = now.plusSeconds(Random.nextLong(1, 6 * 3) * 10), // 10초 ~ 3분, 10초 간격 - test용
            member = selectRandomMember(count)
        )
    }

    private fun selectRandomMember(count: Long): Member {
        return memberRepository.findByIdOrNull(Random.nextLong(count)) ?: selectRandomMember(count)
    }
}