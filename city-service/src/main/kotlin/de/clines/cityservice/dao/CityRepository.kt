package de.clines.cityservice.dao

import de.clines.cityservice.model.City
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface CityRepository: PagingAndSortingRepository<City, Long> {

    @Query("SELECT * FROM cities ORDER BY (abs(lat - ?1) + abs(lng - ?2)) asc limit 1", nativeQuery = true)
    fun findClosestCityByLocation(latitude: Double, longitude: Double): City

    fun findAllByCityStartingWithOrderByPopulationDesc(prefix: String, pageable: Pageable): Page<City>

}