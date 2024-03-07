package com.example.kfd_hw.database.dao

import com.example.kfd_hw.database.entity.AnswerEntity
import jakarta.validation.constraints.Email
import java.util.UUID


interface AnswerDao : CommonDao<AnswerEntity> {
    fun findByAuthorEmailAndQuestionnaireId(authorEmail: String, questionnaireId: Long): AnswerEntity?
    fun findAllByQuestionnaireId(id: Long): Iterable<AnswerEntity>
}