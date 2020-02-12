package de.clines.tours.dto

data class PlaceReplyDto(
        val tourId: String,
        val places: List<PlaceDto>
)