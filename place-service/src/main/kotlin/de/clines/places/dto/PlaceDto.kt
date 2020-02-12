package de.clines.places.dto

import com.google.maps.model.PlacesSearchResult
import de.clines.places.models.Location
import de.clines.places.models.toLocation

data class PlaceDto(
        val id: String,
        val name: String,
        val location: Location,
        val rating: Float,
        val userRatingsTotal: Int,
        val labels: List<String>
)

fun PlacesSearchResult.toPlaceDto(): PlaceDto {
    return PlaceDto(
            id = placeId,
            name = name,
            location = geometry.location.toLocation(),
            rating = rating,
            userRatingsTotal = userRatingsTotal,
            labels = types.toList()
    )
}