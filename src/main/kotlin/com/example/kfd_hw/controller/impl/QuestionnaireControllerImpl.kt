package com.example.kfd_hw.controller.impl

import com.example.kfd_hw.controller.QuestionnaireController
import com.example.kfd_hw.model.message.quesstinaire.*
import com.example.kfd_hw.service.impl.QuestionnaireServiceImpl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/questionnaire")
class QuestionnaireControllerImpl(
    val questionnaireService: QuestionnaireServiceImpl
) : QuestionnaireController {

    @GetMapping
    override fun getAll(): Iterable<QuestionnaireResponse> =
        questionnaireService.getAll()

    @GetMapping("/{id}")
    override fun getById(@PathVariable id: UUID): QuestionnaireResponse =
        questionnaireService.getById(id)
    @GetMapping("/my_answers")
    override fun getMyAnswers(): Iterable<AnswerResponse> =
        questionnaireService.getMyAnswers()
    @PostMapping
    override fun create(@RequestBody request: QuestionnaireRequest): QuestionnaireResponse
        = questionnaireService.create(request)


    @PostMapping("/answer/{id}")
    override fun answer(@RequestBody request: AnswerRequest, @PathVariable id: UUID): AnswerCreatedResponse {
        questionnaireService.answer(request.text, id)
        return AnswerCreatedResponse()
    }
}