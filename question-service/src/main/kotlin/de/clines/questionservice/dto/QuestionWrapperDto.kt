package de.clines.questionservice.dto

import de.clines.questionservice.model.Question
import de.clines.questionservice.model.QuestionType

data class QuestionWrapperDto(
        val questionId: String,
        val type: QuestionType,
        val payload: QuestionDto
)

fun Question.toWrapperDto(): QuestionWrapperDto {
    val type = when(this) {
        is Question.GeneralQuestion -> QuestionType.GENERAL
        is Question.PlaceQuestion -> QuestionType.PLACE
    }
    return QuestionWrapperDto(
            questionId = "${type.name.toLowerCase()}-${id}",
            type = type,
            payload = this.toDto()
    )
}