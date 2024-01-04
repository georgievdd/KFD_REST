package com.example.kfd_hw.controller

import com.example.kfd_hw.model.message.quesstinaire.*
import java.util.UUID

interface QuestionnaireController {
    fun getAll(): Iterable<QuestionnaireResponse>
    fun getById(id: UUID): QuestionnaireResponse
    fun answer(request: AnswerRequest, id: UUID): AnswerCreatedResponse
    fun create(request: QuestionnaireRequest): QuestionnaireResponse
    fun getMyAnswers(): Iterable<AnswerResponse>
}