package de.clines.places.dto

import com.google.maps.model.AddressType
import com.google.maps.model.PlaceDetails
import de.clines.places.models.Comment
import de.clines.places.models.Location
import de.clines.places.models.toComment
import de.clines.places.models.toLocation

data class PlaceDetailsDto(
        val id: String,
        val name: String,
        val photos: List<String>,
        val location: Location,
        val labels: List<String>,
        val rating: Float,
        val comments: List<Comment>
)

fun PlaceDetails.toDto(): PlaceDetailsDto {
    return PlaceDetailsDto(
            id = placeId,
            name = name,
            photos = photos?.map { "https://api.clines.de/places/photos/${it.photoReference}" } ?: emptyList(),
            location = geometry.location.toLocation(),
            rating = rating,
            comments = reviews?.map(PlaceDetails.Review::toComment) ?: emptyList(),
            labels = types.map(AddressType::toCanonicalLiteral)
    )
}