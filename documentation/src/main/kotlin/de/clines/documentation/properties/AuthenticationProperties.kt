package de.clines.documentation.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import kotlin.properties.Delegates

@Component
@ConfigurationProperties(prefix = "clines.service.authentication")
class AuthenticationProperties {
    lateinit var username: String
    lateinit var password: String
    var refreshRate: Long by Delegates.notNull()
}