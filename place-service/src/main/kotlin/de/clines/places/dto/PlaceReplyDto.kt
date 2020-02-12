package de.clines.places.dto

data class PlaceReplyDto (
        val tourId: String,
        val places: List<PlaceDto>
)