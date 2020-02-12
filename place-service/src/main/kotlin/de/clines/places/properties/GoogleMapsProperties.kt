package de.clines.places.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "google.maps")
class GoogleMapsProperties {
    lateinit var key: String
}