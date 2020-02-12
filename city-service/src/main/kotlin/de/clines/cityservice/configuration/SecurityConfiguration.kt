package de.clines.cityservice.configuration

import com.okta.spring.boot.oauth.Okta
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SecurityConfiguration: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.oauth2ResourceServer {
            it.jwt()
        }

        http.authorizeRequests {
            it.antMatchers("/actuator/health").permitAll()
            it.antMatchers("/v2/api-docs").permitAll()
            it.antMatchers("/closest").authenticated()
            it.antMatchers("/autocomplete").authenticated()
            it.anyRequest().denyAll()
        }

        Okta.configureResourceServer401ResponseBody(http)
    }

}