package com.example.kfd_hw.controller.impl

import com.example.kfd_hw.controller.QuestionnaireController
import com.example.kfd_hw.database.entity.QuestionnaireType
import com.example.kfd_hw.model.message.quesstinaire.*
import com.example.kfd_hw.service.impl.QuestionnaireServiceImpl
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/questionnaire")
class QuestionnaireControllerImpl(
    private val service: QuestionnaireServiceImpl
) : QuestionnaireController {
    @GetMapping
    override fun getAll(): Iterable<QuestionnaireCommon> =
        service.getAll()

    @GetMapping("/{id}")
    override fun getAllAnswers(@PathVariable id: Long): QuestionnaireWithAnswers =
        service.getAllAnswers(id)

    @PostMapping("/answer/{id}")
    override fun answer(@RequestBody request: AnswerRequest, @PathVariable id: Long): QuestionnaireAnswerResponse {
        val authorEmail = SecurityContextHolder.getContext().authentication.name
        return service.answer(authorEmail, request, id)
    }

    @PostMapping("/one_answer")
    override fun createOneAnswer(@RequestBody request: QuestionnaireOneAnswerRequest): QuestionnaireOneAnswerResponse =
        service.createOneAnswer(request, QuestionnaireType.OneAnswer)

    @PostMapping("/few_answers")
    override fun createFewAnswers(@RequestBody request: QuestionnaireFewAnswersRequest): QuestionnaireFewAnswersResponse =
        service.createFewAnswers(request, QuestionnaireType.FewAnswers)

    @PostMapping("/free_answer")
    override fun createFreeAnswer(@RequestBody request: QuestionnaireFreeAnswerRequest): QuestionnaireFreeAnswerResponse {
        println(request)
        return service.createFreeAnswer(request, QuestionnaireType.FreeAnswer)
    }

}