package de.clines.questionservice.dto

import de.clines.questionservice.model.Comment
import de.clines.questionservice.model.Location

data class PlaceDetailsDto(
        val id: String,
        val name: String,
        val photos: List<String>,
        val location: Location,
        val labels: List<String>,
        val rating: Float,
        val comments: List<Comment>
)