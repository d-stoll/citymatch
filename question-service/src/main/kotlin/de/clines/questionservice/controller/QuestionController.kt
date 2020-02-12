package de.clines.questionservice.controller

import de.clines.questionservice.dto.QuestionWrapperDto
import de.clines.questionservice.dto.toWrapperDto
import de.clines.questionservice.service.QuestionService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@Api(tags = ["Place Questions"], description = "Endpoints for retrieving information about place questions.")
@RestController
class QuestionController(
        private val questionService: QuestionService
) {

    @ApiOperation("Returns the place question with id=questionId", response = QuestionWrapperDto::class,
            notes = "The type of the question is defined by a prefix in the questionId. The prefix can " +
                    "either be 'general' or 'place'. Questions with type 'general' are loaded from a " +
                    "self hosted database, while questions with type 'place' are fetched from the " +
                    "Google Maps Place API.")
    @GetMapping("/{questionId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun getQuestion(@PathVariable("questionId") questionId: String): QuestionWrapperDto {
        return questionService.getQuestion(questionId)?.toWrapperDto()
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

}