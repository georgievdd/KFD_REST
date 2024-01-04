package com.example.kfd_hw.database.dao

import com.example.kfd_hw.database.entity.QuestionnaireEntity

interface QuestionnaireDao : CommonDao<QuestionnaireEntity> {
    fun findByQuestion(question: String): QuestionnaireEntity?
}