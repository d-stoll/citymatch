package de.clines.questionservice.dto

import de.clines.questionservice.model.Question

data class GeneralQuestionModelDto(
        val id: String,
        val tags: List<String>
)

fun Question.GeneralQuestion.toGeneralQuestionModelDto(): GeneralQuestionModelDto {
    return GeneralQuestionModelDto(id, tags)
}