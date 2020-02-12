package de.clines.cityservice.dto

import de.clines.cityservice.model.City
import de.clines.cityservice.model.Location

data class CityDto(
        val id: Long,
        val name: String,
        val location: Location,
        val country: String,
        val iso2: String
)

fun City.toDto() = CityDto(id, city, Location(lat, lng), country, iso2)