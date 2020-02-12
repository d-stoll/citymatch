package de.clines.tours.dto

import de.clines.tours.model.Location
import de.clines.tours.model.Tour
import java.time.LocalDateTime

data class TourDto(
        val id: String,
        val name: String,
        val time: Int,
        val location: Location,
        val finished: Boolean,
        val createdAt: LocalDateTime,
        val nextQuestionId: String?
)

fun Tour.toDto(): TourDto {
    return TourDto(id, name, time,  location, finished, createdAt, nextQuestionId)
}