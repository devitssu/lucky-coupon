package com.teamsparta.luckycoupon.domain.member.controller

import com.teamsparta.luckycoupon.domain.member.dto.SignUpRequest
import com.teamsparta.luckycoupon.domain.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequest): ResponseEntity<Unit> {
        memberService.signUp(request)
        return ResponseEntity
            .status(HttpStatus.OK)
            .build()
    }
}