package com.example.kfd_hw.service

import com.example.kfd_hw.model.message.quesstinaire.AnswerCreatedResponse
import com.example.kfd_hw.model.message.quesstinaire.AnswerResponse
import com.example.kfd_hw.model.message.quesstinaire.QuestionnaireRequest
import com.example.kfd_hw.model.message.quesstinaire.QuestionnaireResponse
import java.util.UUID

interface QuestionnaireService {
    fun getAll(): Iterable<QuestionnaireResponse>
    fun getById(id: UUID): QuestionnaireResponse
    fun answer(text: String, questionnaireId: UUID)
    fun create(request: QuestionnaireRequest): QuestionnaireResponse

    fun getMyAnswers(): Iterable<AnswerResponse>
}