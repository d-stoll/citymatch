package de.clines.cityservice.service

import de.clines.cityservice.dao.CityRepository
import de.clines.cityservice.model.City
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class CityService(
        private val cityRepository: CityRepository
) {

    fun findClosestCity(latitude: Double, longitude: Double): City {
        return cityRepository.findClosestCityByLocation(latitude, longitude)
    }

    fun findAutocompletesForPrefix(prefix: String): List<City> {
        val pageable = PageRequest.of(0, 5, Sort.by("population").descending())
        return cityRepository.findAllByCityStartingWithOrderByPopulationDesc(prefix, pageable).content
    }

}