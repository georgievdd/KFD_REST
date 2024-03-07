package com.example.kfd_hw.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Entity
class QuestionnaireEntity(
    @Column(nullable = false, length = 250)
    var question: String = "",
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    var type: QuestionnaireType = QuestionnaireType.OneAnswer,
    @Column(nullable = false, length = 500)
    var payload: String = ""
) : AbstractEntity()

enum class QuestionnaireType {
    OneAnswer, FewAnswers, FreeAnswer
}

const val AnswerSeparator = "!{'&3}"