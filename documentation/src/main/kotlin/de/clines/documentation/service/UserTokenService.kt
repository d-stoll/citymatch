package de.clines.documentation.service

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.stereotype.Service

@Service
class UserTokenService(
        private val oAuth2AuthorizedClientService: OAuth2AuthorizedClientService
) {

    fun getUserAccessToken(): String {
        val authentication = SecurityContextHolder.getContext().authentication as OAuth2AuthenticationToken
        val client: OAuth2AuthorizedClient = oAuth2AuthorizedClientService.loadAuthorizedClient(
                authentication.authorizedClientRegistrationId, authentication.name)
        return client.accessToken.tokenValue
    }

}