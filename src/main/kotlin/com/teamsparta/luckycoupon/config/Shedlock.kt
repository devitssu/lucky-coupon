package com.teamsparta.luckycoupon.config

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.sql.Timestamp

@Entity
class Shedlock(
    @Id
    val name: String,
    val lockUntil: Timestamp,
    val lockedAt: Timestamp,
    val lockedBy: String,
)