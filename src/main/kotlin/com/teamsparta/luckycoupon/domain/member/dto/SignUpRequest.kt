package com.teamsparta.luckycoupon.domain.member.dto

import com.teamsparta.luckycoupon.domain.member.model.Member

data class SignUpRequest(
    val username: String,
    val password: String
) {
    fun toEntity(): Member {
        return Member(
            username = this.username,
            password = this.password
        )
    }
}
