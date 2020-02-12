package de.clines.documentation.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import kotlin.properties.Delegates

@Component
@ConfigurationProperties(prefix = "clines.swagger")
class SwaggerProperties {
    var refreshRate: Long by Delegates.notNull()
    lateinit var services: Map<String, String>
}