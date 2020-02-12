package de.clines.tours.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document("tours")
data class Tour(
        @Id
        val id: String = UUID.randomUUID().toString(),
        var name: String,
        val username: String,
        val time: Int,
        val location: Location,
        var finished: Boolean,
        val createdAt: LocalDateTime,
        var nextQuestionId: String?,
        val generalQuestions: MutableList<GeneralQuestionModel>,
        val placeQuestions: MutableList<PlaceQuestionModel>,
        val userModel: MutableMap<String, Double>,
        val route: MutableList<String> = mutableListOf()
)