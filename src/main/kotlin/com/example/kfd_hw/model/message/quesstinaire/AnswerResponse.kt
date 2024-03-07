package com.example.kfd_hw.model.message.quesstinaire

import jakarta.persistence.Column
import java.time.LocalDateTime
import java.util.*

data class AnswerResponse(
    val id: Long,
    val body: Iterable<String>,
    val authorEmail: String,
    val questionnaireId: Long,
    val createdAt: LocalDateTime,
)
