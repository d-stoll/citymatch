package de.clines.places.configuration

import com.okta.spring.boot.oauth.Okta
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfiguration: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.oauth2ResourceServer {
            it.jwt()
        }
        http.authorizeRequests {
            it.antMatchers("/actuator/health").permitAll()
            it.antMatchers("/v2/api-docs").permitAll()
            it.antMatchers("/tourist-attractions").authenticated()
            it.antMatchers("/details/*").authenticated()
            it.antMatchers("/photos/*").permitAll()
            it.anyRequest().denyAll()
        }
        Okta.configureResourceServer401ResponseBody(http)
    }


}