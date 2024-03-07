package com.example.kfd_hw.controller

import com.example.kfd_hw.model.message.quesstinaire.*

interface QuestionnaireController {
    fun getAll(): Iterable<QuestionnaireCommon>

    fun getAllAnswers(id: Long): QuestionnaireWithAnswers
    fun answer(request: AnswerRequest, id: Long): QuestionnaireAnswerResponse
    fun createOneAnswer(request: QuestionnaireOneAnswerRequest): QuestionnaireOneAnswerResponse
    fun createFewAnswers(request: QuestionnaireFewAnswersRequest): QuestionnaireFewAnswersResponse
    fun createFreeAnswer(request: QuestionnaireFreeAnswerRequest): QuestionnaireFreeAnswerResponse
}