package de.clines.places.configuration

import com.google.maps.GeoApiContext
import de.clines.places.properties.GoogleMapsProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GoogleMapsConfiguration(
        private val googleMapsProperties: GoogleMapsProperties
) {

    @Bean
    fun geoApiContext(): GeoApiContext {
        return GeoApiContext.Builder()
                .apiKey(googleMapsProperties.key)
                .build()
    }


}