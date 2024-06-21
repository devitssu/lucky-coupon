package com.teamsparta.luckycoupon.domain.member.repository

import com.teamsparta.luckycoupon.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun existsByUsername(username: String): Boolean
}
