package de.clines.questionservice.dto

import de.clines.questionservice.model.Comment
import de.clines.questionservice.model.Location
import de.clines.questionservice.model.Question

sealed class QuestionDto {
    abstract val id: String
    abstract val text: String
    abstract val photos: List<String>

    data class GeneralQuestionDto(
            override val id: String,
            override val text: String,
            override val photos: List<String>
    ) : QuestionDto()

    data class PlaceQuestionDto(
            override val id: String,
            val name: String,
            override val text: String,
            val location: Location,
            override val photos: List<String>,
            val comments: List<Comment>,
            val rating: Float
    ) : QuestionDto()
}

fun Question.GeneralQuestion.toDto() = QuestionDto.GeneralQuestionDto(id, text, photos)

fun Question.PlaceQuestion.toDto() = QuestionDto.PlaceQuestionDto(id, name, text,
        location, photos, comments, rating)

fun Question.toDto(): QuestionDto {
    return when(this) {
        is Question.PlaceQuestion -> this.toDto()
        is Question.GeneralQuestion -> this.toDto()
    }
}
