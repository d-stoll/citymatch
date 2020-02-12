package de.clines.questionservice.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("clines")
class ServiceUriProperties {
    lateinit var services: Map<String, String>
}