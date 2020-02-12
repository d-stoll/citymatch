package de.clines.places.models

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.google.maps.model.PlaceDetails
import java.time.LocalDateTime
import java.time.ZoneOffset

data class Comment(
        val author: String,
        val authorProfilePictureUrl: String,
        @get:JsonSerialize(using = LocalDateTimeSerializer::class)
        val time: LocalDateTime,
        val rating: Int,
        val text: String
)

fun PlaceDetails.Review.toComment(): Comment {
    return Comment(
            author = authorName,
            authorProfilePictureUrl = profilePhotoUrl,
            time = LocalDateTime.ofInstant(time, ZoneOffset.UTC),
            rating = rating,
            text = text
    )
}