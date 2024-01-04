package com.example.kfd_hw.model.message.quesstinaire

import jakarta.persistence.Column
import java.time.LocalDateTime
import java.util.*

data class AnswerResponse(
    val id: UUID,
    val body: String,
    val authorEmail: String,
    val questionnaireId: UUID,
    val createdAt: LocalDateTime,
)
