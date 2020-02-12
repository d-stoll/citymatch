package de.clines.places

import com.google.maps.GeoApiContext
import com.google.maps.PlacesApi
import com.google.maps.model.LatLng
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlaceServiceApplication

fun main(args: Array<String>) {
	runApplication<PlaceServiceApplication>(*args)
}