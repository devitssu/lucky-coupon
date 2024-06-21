package com.teamsparta.luckycoupon.init

import com.teamsparta.luckycoupon.domain.member.model.Member
import com.teamsparta.luckycoupon.domain.member.repository.MemberRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DatabaseInitializer(
    private val memberRepository: MemberRepository
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val memberList = mutableListOf<Member>()
        for (i in 1..100) {
            memberList.add(Member(username = "test$i", password = "test$i"))
        }
        memberRepository.saveAll(memberList)
    }
}