package de.clines.questionservice.controller

import de.clines.questionservice.dto.GeneralQuestionModelDto
import de.clines.questionservice.dto.toGeneralQuestionModelDto
import de.clines.questionservice.model.Question
import de.clines.questionservice.service.GeneralQuestionService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["Random Questions"], description = "Endpoints for fetching initial questions for the tour creation.")
@ExperimentalCoroutinesApi
@RestController
class RandomQuestionController(
        private val generalQuestionService: GeneralQuestionService
) {

    @ApiOperation("Returns a collection of questions.", response = GeneralQuestionModelDto::class,
            responseContainer = "List",
            notes = "Returns a collection of questions that can be used to initialize the Tour Model. " +
                    "Currently only the strategy \"random\" and the type \"general\" is supported. " +
                    "With the variable \"count\" the number of questions can be defined.")
    @GetMapping(params = ["type=general", "strategy=random"])
    suspend fun getRandomGeneralQuestions(@RequestParam("count") count: Int): List<GeneralQuestionModelDto> {
        return generalQuestionService.findAllGeneralQuestions()
                .toList()
                .shuffled()
                .take(5)
                .map(Question.GeneralQuestion::toGeneralQuestionModelDto)
    }

}