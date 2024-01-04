package com.example.kfd_hw.model.message.quesstinaire

import org.jetbrains.annotations.NotNull

data class QuestionnaireRequest(
    @field:NotNull
    val title: String,
    @field:NotNull
    val question: String,
)
