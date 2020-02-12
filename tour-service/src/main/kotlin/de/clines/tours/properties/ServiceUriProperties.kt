package de.clines.tours.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("clines.services")
class ServiceUriProperties {
    lateinit var questionService: String
}