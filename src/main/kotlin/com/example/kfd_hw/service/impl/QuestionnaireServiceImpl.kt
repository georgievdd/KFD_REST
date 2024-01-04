package com.example.kfd_hw.service.impl

import com.example.kfd_hw.database.dao.AnswerDao
import com.example.kfd_hw.database.dao.QuestionnaireDao
import com.example.kfd_hw.database.entity.AnswerEntity
import com.example.kfd_hw.database.entity.QuestionnaireEntity
import com.example.kfd_hw.model.exception.AlreadyExistException
import com.example.kfd_hw.model.exception.NotFoundException
import com.example.kfd_hw.model.message.quesstinaire.AnswerCreatedResponse
import com.example.kfd_hw.model.message.quesstinaire.AnswerResponse
import com.example.kfd_hw.model.message.quesstinaire.QuestionnaireRequest
import com.example.kfd_hw.model.message.quesstinaire.QuestionnaireResponse
import com.example.kfd_hw.service.QuestionnaireService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class QuestionnaireServiceImpl(
    private val questionnaireDao: QuestionnaireDao,
    private val answerDao: AnswerDao,
    ) : QuestionnaireService {
    override fun getAll(): Iterable<QuestionnaireResponse> =
        questionnaireDao.findAll().map { questionnaire -> questionnaire.mapToQuestionnaireResponse() }

    override fun getById(id: UUID): QuestionnaireResponse =
        questionnaireDao.findEntityById(id)?.mapToQuestionnaireResponse() ?: throw NotFoundException()

    override fun answer(text: String, questionnaireId: UUID) {
        val email = SecurityContextHolder.getContext().authentication.name
        if (answerDao.findByAuthorEmailAndQuestionnaireId(email, questionnaireId) != null) throw AlreadyExistException()
        answerDao.save(AnswerEntity(
            authorEmail = email,
            body = text,
            questionnaireId = questionnaireId
        ))
    }

    override fun create(request: QuestionnaireRequest): QuestionnaireResponse {
        if (questionnaireDao.findByQuestion(request.question) != null)
            throw AlreadyExistException()
        return questionnaireDao.save(request.mapToEntity()).mapToQuestionnaireResponse()
    }

    override fun getMyAnswers(): Iterable<AnswerResponse> {
        val email = SecurityContextHolder.getContext().authentication.name
        return answerDao.findAllByAuthorEmail(email).map { answer -> answer.mapToResponse() }
    }

    private fun QuestionnaireEntity.mapToQuestionnaireResponse() =
        QuestionnaireResponse(
            id = id,
            title = title,
            question = question,
        )

    private fun QuestionnaireRequest.mapToEntity() =
        QuestionnaireEntity(
            title = title,
            question = question,
        )

    private fun AnswerEntity.mapToResponse() =
        AnswerResponse(
            id = id,
            body = body,
            authorEmail = authorEmail,
            questionnaireId = questionnaireId,
            createdAt = createdAt
        )
}