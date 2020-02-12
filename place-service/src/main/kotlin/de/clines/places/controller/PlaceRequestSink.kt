package de.clines.places.controller

import de.clines.places.dto.PlaceRequestDto
import de.clines.places.service.PlaceService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink

@EnableBinding(Sink::class)
class PlaceRequestSink(
        private val placeService: PlaceService
) {

    private val log = LoggerFactory.getLogger(PlaceRequestSink::class.java)

    @StreamListener(Sink.INPUT)
    fun handlePlaceRequest(placeRequestDto: PlaceRequestDto) {
        log.info("Received place request: $placeRequestDto")
        placeService.getTouristAttractionsAroundLocation(placeRequestDto.tourId, placeRequestDto.location)
    }

}