package de.clines.tours.dto

import de.clines.tours.model.Location

data class PlaceRequestDto(
        val tourId: String,
        val location: Location
)