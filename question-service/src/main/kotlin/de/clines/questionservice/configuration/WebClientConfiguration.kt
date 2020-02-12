package de.clines.questionservice.configuration

import de.clines.questionservice.properties.ServiceUriProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfiguration(
        private val serviceUriProperties: ServiceUriProperties
) {

    @Bean("placeServiceWebClient")
    fun webClient(): WebClient {
        return WebClient.create(serviceUriProperties.services["place-service"] ?: error("No place-service uri defined"))
    }
}