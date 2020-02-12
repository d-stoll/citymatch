package de.clines.tours.service

import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UsernameService {

    suspend fun getUsername(): String = ReactiveSecurityContextHolder.getContext().awaitSingle().authentication.name

}