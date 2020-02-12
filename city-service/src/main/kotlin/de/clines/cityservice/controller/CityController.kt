package de.clines.cityservice.controller

import de.clines.cityservice.dto.CityDto
import de.clines.cityservice.dto.toDto
import de.clines.cityservice.model.City
import de.clines.cityservice.service.CityService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["City Questions"], description = "Endpoints for querying cities")
@RestController
class CityController(
        private val cityService: CityService
) {

    @ApiOperation("Returns the nearest city to a geolocation")
    @GetMapping("/closest")
    fun findAutocompletes(@RequestParam("latitude") latitude: Double,
                          @RequestParam("longitude") longitude: Double): CityDto {
        return cityService.findClosestCity(latitude, longitude).toDto()
    }

    @ApiOperation("Returns the most populated places, which match the prefix")
    @GetMapping("/autocomplete")
    fun findAutocompletes(@RequestParam("prefix") prefix: String): List<CityDto> {
        return cityService.findAutocompletesForPrefix(prefix).map(City::toDto)
    }

}