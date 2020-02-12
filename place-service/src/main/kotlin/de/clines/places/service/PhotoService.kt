package de.clines.places.service

import com.google.maps.GeoApiContext
import com.google.maps.PlacesApi
import org.springframework.stereotype.Service

@Service
class PhotoService(
        private val geoApiContext: GeoApiContext
) {

    fun fetchPhoto(photoReference: String, maxWidth: Int, maxHeight: Int): ByteArray {
        return PlacesApi.photo(geoApiContext, photoReference)
                .maxWidth(maxWidth)
                .maxHeight(maxHeight)
                .await().imageData
    }

}