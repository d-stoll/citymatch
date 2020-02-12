package de.clines.tours.dto

import de.clines.tours.model.Location

data class CreateTourDto(
        val name: String,
        val time: Int,
        val location: Location
)