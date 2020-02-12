package de.clines.tours.configuration

import com.okta.spring.boot.oauth.Okta
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
class SecurityConfiguration {

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.oauth2ResourceServer {
            it.jwt()
        }

        Okta.configureResourceServer401ResponseBody(http)

        http.authorizeExchange {
            it.pathMatchers("/actuator/health").permitAll()
            it.pathMatchers("/v2/api-docs").permitAll()
            it.pathMatchers("/*").authenticated()
            it.pathMatchers("/*/answer").authenticated()
            it.pathMatchers("/*/route").authenticated()
            it.anyExchange().denyAll()
        }

        return http.build()
    }

}