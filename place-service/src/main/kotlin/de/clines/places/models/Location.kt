package de.clines.places.models

import com.google.maps.model.LatLng

data class Location(
        val latitude: Double,
        val longitude: Double
){
    fun toLatLng() = LatLng(latitude, longitude)
}

fun LatLng.toLocation() = Location(lat, lng)