package de.clines.places.service

import com.google.maps.GeoApiContext
import com.google.maps.PlaceDetailsRequest
import com.google.maps.PlacesApi
import de.clines.places.dto.PlaceDetailsDto
import de.clines.places.dto.toDto
import org.springframework.stereotype.Service

@Service
class PlaceDetailsService(
        private val geoApiContext: GeoApiContext
        ) {

    fun getPlaceById(placeId: String): PlaceDetailsDto? {
        return PlacesApi
                .placeDetails(geoApiContext, placeId)
                .fields(PlaceDetailsRequest.FieldMask.PLACE_ID,
                        PlaceDetailsRequest.FieldMask.NAME,
                        PlaceDetailsRequest.FieldMask.GEOMETRY,
                        PlaceDetailsRequest.FieldMask.PHOTOS,
                        PlaceDetailsRequest.FieldMask.TYPES,
                        PlaceDetailsRequest.FieldMask.REVIEW,
                        PlaceDetailsRequest.FieldMask.RATING)
                .await()
                .toDto()
    }

}