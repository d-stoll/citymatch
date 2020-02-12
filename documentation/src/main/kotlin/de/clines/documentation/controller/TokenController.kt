package de.clines.documentation.controller

import de.clines.documentation.dto.AccessTokenDto
import de.clines.documentation.service.UserTokenService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TokenController(
        private val userTokenService: UserTokenService
) {

    /**
     * Endpoint for getting a user access token for testing.
     */
    @GetMapping("/access-token", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAccessToken() = AccessTokenDto(userTokenService.getUserAccessToken())

}