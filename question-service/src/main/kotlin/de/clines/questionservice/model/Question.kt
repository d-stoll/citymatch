package de.clines.questionservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

sealed class Question {
    abstract val id: String
    abstract val text: String
    abstract val photos: List<String>
    abstract val tags: List<String>

    @Document("generalQuestions")
    data class GeneralQuestion(
            @Id
            override val id: String = UUID.randomUUID().toString(),
            override val photos: List<String>,
            override val tags: List<String>,
            override val text: String
    ) : Question()

    data class PlaceQuestion(
            override val id: String,
            val name: String,
            override val text: String,
            val location: Location,
            override val photos: List<String>,
            override val tags: List<String>,
            val comments: List<Comment>,
            val rating: Float
    ) : Question()
}