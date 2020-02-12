package de.clines.documentation.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@EnableWebSecurity
@Configuration
class SecurityConfiguration: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.oauth2Client()
        http.oauth2Login()
        http.authorizeRequests {
            it.antMatchers("/actuator/health").permitAll()
            it.anyRequest().authenticated()
        }
    }
}