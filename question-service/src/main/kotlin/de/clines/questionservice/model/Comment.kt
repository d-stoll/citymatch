package de.clines.questionservice.model

import java.time.LocalDateTime

data class Comment(
        val author: String,
        val authorProfilePictureUrl: String,
        val time: LocalDateTime,
        val rating: Int,
        val text: String
)