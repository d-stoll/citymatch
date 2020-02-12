package de.clines.places.controller

import de.clines.places.dto.PlaceDetailsDto
import de.clines.places.service.PlaceDetailsService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@Api(tags = ["Place Details"], description = "Endpoints for retrieving detail information about specific places")
@RequestMapping("/details")
@RestController
class PlaceDetailsController(private val placeDetailsService: PlaceDetailsService) {

    @ApiOperation("Returns details about the place with id=placeId",
            notes = "Fetches detail information about the specified place from the Google Maps Places API")
    @GetMapping("/{placeId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getPlace(@PathVariable("placeId") placeId: String): PlaceDetailsDto {
        return placeDetailsService.getPlaceById(placeId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

}