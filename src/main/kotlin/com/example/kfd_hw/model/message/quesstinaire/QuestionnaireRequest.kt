package com.example.kfd_hw.model.message.quesstinaire

import org.jetbrains.annotations.NotNull

data class QuestionnaireFreeAnswerRequest(
    @field:NotNull
    val question: String,
)

data class QuestionnaireOneAnswerRequest(
    @field:NotNull
    val question: String,
    @field:NotNull
    val answers: Iterable<String>,
)

data class QuestionnaireFewAnswersRequest(
    @field:NotNull
    val question: String,
    @field:NotNull
    val answers: Iterable<String>,
)

