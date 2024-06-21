package com.teamsparta.luckycoupon.domain.member.service

import com.teamsparta.luckycoupon.domain.member.dto.SignUpRequest
import com.teamsparta.luckycoupon.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun signUp(request: SignUpRequest) {
        check(!memberRepository.existsByUsername(request.username)) { throw RuntimeException() }
        memberRepository.save(request.toEntity())
    }
}
