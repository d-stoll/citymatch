package de.clines.places.dto

import de.clines.places.models.Location

data class PlaceRequestDto(
        val tourId: String,
        val location: Location
)