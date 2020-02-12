package de.clines.places.service

import com.google.maps.GeoApiContext
import com.google.maps.PlacesApi
import com.google.maps.errors.ApiException
import com.google.maps.model.PlacesSearchResponse
import com.google.maps.model.PlacesSearchResult
import de.clines.places.dto.PlaceReplyDto
import de.clines.places.dto.toPlaceDto
import de.clines.places.models.Location
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source
import org.springframework.messaging.support.GenericMessage
import org.springframework.stereotype.Service

@Service
@EnableBinding(Source::class)
class PlaceService(
        private val geoApiContext: GeoApiContext,
        private val source: Source
) {

    fun getTouristAttractionsAroundLocation(tourId: String, location: Location) {
        var response = PlacesApi.nearbySearchQuery(geoApiContext, location.toLatLng())
                .radius(20000)
                .keyword("point of interest")
                .await()
        sendPlaceReplyMessage(tourId, response.results)
        while(response.nextPageToken != null) {
            try {
                response = getNextPage(response.nextPageToken)
                sendPlaceReplyMessage(tourId, response.results)
            } catch (ex: ApiException) {
                return
            }
        }
    }

    private fun getNextPage(nextPageToken: String): PlacesSearchResponse
            = PlacesApi.nearbySearchNextPage(geoApiContext, nextPageToken).await()

    private fun sendPlaceReplyMessage(tourId: String, searchResults: Array<PlacesSearchResult>) {
        source.output().send(GenericMessage(PlaceReplyDto(tourId, searchResults.map(PlacesSearchResult::toPlaceDto))))
    }

}