package com.example.kfd_hw.service.impl

import com.example.kfd_hw.database.dao.AnswerDao
import com.example.kfd_hw.database.dao.QuestionnaireDao
import com.example.kfd_hw.database.entity.AnswerEntity
import com.example.kfd_hw.database.entity.AnswerSeparator
import com.example.kfd_hw.database.entity.QuestionnaireEntity
import com.example.kfd_hw.database.entity.QuestionnaireType
import com.example.kfd_hw.model.exception.AlreadyExistException
import com.example.kfd_hw.model.exception.BadRequestException
import com.example.kfd_hw.model.exception.NotFoundException
import com.example.kfd_hw.model.message.quesstinaire.*
import com.example.kfd_hw.service.QuestionnaireService
import org.springframework.stereotype.Service

@Service
class QuestionnaireServiceImpl(
    private val questionnaireDao: QuestionnaireDao,
    private val answerDao: AnswerDao
) : QuestionnaireService {
    override fun getAll(): Iterable<QuestionnaireCommon> {
        return questionnaireDao.findAll().map { questionnaire -> questionnaire.mapToCommon() as QuestionnaireCommon }
    }

    override fun getAllAnswers(id: Long): QuestionnaireWithAnswers {
        val questionnaire = questionnaireDao.findEntityById(id) ?: throw NotFoundException()
        return QuestionnaireWithAnswers(
            questionnaire = questionnaire.mapToCommon(),
            answers = answerDao.findAllByQuestionnaireId(id).map {
                answer -> answer.mapToResponse(questionnaire)
            }
        )
    }

    private fun validateAnswer(answers: Iterable<String>, questionnaire: QuestionnaireEntity) {
        if (questionnaire.type == QuestionnaireType.FreeAnswer) {
            if (answers.toList().size != 1) {
                throw BadRequestException()
            }
        }
        if (questionnaire.type == QuestionnaireType.OneAnswer) {
            if (answers.toList().size != 1) {
                throw BadRequestException()
            }
        }
    }

    override fun answer(authorEmail: String, request: AnswerRequest, id: Long): QuestionnaireAnswerResponse {
        println(authorEmail)
        println(id)
        println(answerDao.findByAuthorEmailAndQuestionnaireId(authorEmail, id))
        if (answerDao.findByAuthorEmailAndQuestionnaireId(authorEmail, id) != null)
            throw AlreadyExistException()
        val questionnaire = questionnaireDao.findEntityById(id) ?: throw NotFoundException()
        validateAnswer(request.data, questionnaire)
        val answer = answerDao.save(AnswerEntity(
            body = request.data.joinToString(AnswerSeparator),
            authorEmail = authorEmail,
            questionnaireId = id
        ))
        return QuestionnaireAnswerResponse(
            answer = answer.mapToResponse(questionnaire),
            questionnaire = questionnaire.mapToCommon()
        )
    }

    override fun createOneAnswer(request: QuestionnaireOneAnswerRequest, type: QuestionnaireType): QuestionnaireOneAnswerResponse {
        if (questionnaireDao.findByQuestion(request.question) != null)
            throw AlreadyExistException()
        val questionnaire = questionnaireDao.save(QuestionnaireEntity(
            type = type,
            question = request.question,
            payload = request.answers.joinToString(AnswerSeparator),
        ))
        return QuestionnaireOneAnswerResponse(
            id = questionnaire.id,
            type = type,
            question = request.question,
            answers = request.answers,
        )
    }

    override fun createFewAnswers(request: QuestionnaireFewAnswersRequest, type: QuestionnaireType): QuestionnaireFewAnswersResponse {
        if (questionnaireDao.findByQuestion(request.question) != null)
            throw AlreadyExistException()
        val questionnaire = questionnaireDao.save(QuestionnaireEntity(
            type = type,
            question = request.question,
            payload = request.answers.joinToString(AnswerSeparator),
        ))
        return QuestionnaireFewAnswersResponse(
            id = questionnaire.id,
            type = type,
            question = request.question,
            answers = request.answers,
        )
    }

    override fun createFreeAnswer(request: QuestionnaireFreeAnswerRequest, type: QuestionnaireType): QuestionnaireFreeAnswerResponse {
        if (questionnaireDao.findByQuestion(request.question) != null)
            throw AlreadyExistException()
        val questionnaire = questionnaireDao.save(QuestionnaireEntity(
            type = type,
            question = request.question,
            payload = "",
        ))
        return QuestionnaireFreeAnswerResponse(
            id = questionnaire.id,
            type = type,
            question = request.question,
        )
    }


    private fun QuestionnaireEntity.mapToCommon(): QuestionnaireCommon =
        QuestionnaireCommon(
            question = question,
            id = id,
            type = type,
            answers = when(type) {
                QuestionnaireType.FreeAnswer -> null
                QuestionnaireType.FewAnswers -> getAnswersFromString(payload)
                QuestionnaireType.OneAnswer -> getAnswersFromString(payload)
            },
        )

    private fun prepareMultiAnswers(questionnaire: QuestionnaireEntity, body: String): Iterable<String> {
        val answers = questionnaire.payload.split(AnswerSeparator)
        return body.split(AnswerSeparator).map { it ->
            answers[it.toInt()]
        }
    }

    private fun AnswerEntity.mapToResponse(questionnaire: QuestionnaireEntity): AnswerResponse =
        AnswerResponse(
            id = id,
            body = when (questionnaire.type) {
                QuestionnaireType.FreeAnswer -> listOf(body)
                QuestionnaireType.OneAnswer -> prepareMultiAnswers(questionnaire, body)
                QuestionnaireType.FewAnswers -> prepareMultiAnswers(questionnaire, body)
            },
            authorEmail = authorEmail,
            questionnaireId = questionnaire.id,
            createdAt = createdAt,
        )

    private fun getAnswersFromString(answers: String) =
        answers.split(AnswerSeparator)
}