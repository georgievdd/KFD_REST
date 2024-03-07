package com.example.kfd_hw.service

import com.example.kfd_hw.database.entity.QuestionnaireType
import com.example.kfd_hw.model.message.quesstinaire.*

interface QuestionnaireService {
    fun getAll(): Iterable<QuestionnaireCommon>

    fun getAllAnswers(id: Long): QuestionnaireWithAnswers
    fun answer(authorEmail: String, request: AnswerRequest, id: Long): QuestionnaireAnswerResponse
    fun createOneAnswer(request: QuestionnaireOneAnswerRequest, type: QuestionnaireType): QuestionnaireOneAnswerResponse
    fun createFewAnswers(request: QuestionnaireFewAnswersRequest, type: QuestionnaireType): QuestionnaireFewAnswersResponse
    fun createFreeAnswer(request: QuestionnaireFreeAnswerRequest, type: QuestionnaireType): QuestionnaireFreeAnswerResponse
}