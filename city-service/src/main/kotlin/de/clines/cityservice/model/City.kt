package de.clines.cityservice.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "cities")
data class City(
        @Id
        val id: Long = 0,
        val city: String = "",
        val cityAscii: String? = "",
        val lat: Double = 0.0,
        val lng: Double = 0.0,
        val country: String = "",
        val iso2: String = "",
        val iso3: String = "",
        val admin_name: String? = "",
        val population: Long? = 0
)