package com.example.kfd_hw.model.message.quesstinaire

import com.example.kfd_hw.database.entity.QuestionnaireType
import java.util.UUID

data class QuestionnaireFreeAnswerResponse(
    val question: String = "",
    val id: Long,
    val type: QuestionnaireType,
)

data class QuestionnaireOneAnswerResponse(
    val question: String = "",
    val id: Long,
    val answers: Iterable<String>,
    val type: QuestionnaireType,
)

data class QuestionnaireFewAnswersResponse(
    val question: String = "",
    val id: Long,
    val answers: Iterable<String>,
    val type: QuestionnaireType,
)

data class QuestionnaireCommon(
    val question: String = "",
    val id: Long,
    val answers: Iterable<String>?,
    val type: QuestionnaireType,
)

data class QuestionnaireWithAnswers(
    val questionnaire: QuestionnaireCommon,
    val answers: Iterable<AnswerResponse>,
)