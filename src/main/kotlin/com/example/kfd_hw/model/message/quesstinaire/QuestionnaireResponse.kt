package com.example.kfd_hw.model.message.quesstinaire

import java.util.UUID

data class QuestionnaireResponse(
    val title: String,
    val question: String = "",
    val id: UUID,
)