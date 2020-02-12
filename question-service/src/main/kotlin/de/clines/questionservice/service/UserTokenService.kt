package de.clines.questionservice.service

import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Service

@Service
class UserTokenService {

    suspend fun getUserAccessToken(): String {
        val context = ReactiveSecurityContextHolder.getContext().awaitSingle()
        return (context.authentication as JwtAuthenticationToken).token.tokenValue
    }

}