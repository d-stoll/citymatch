package de.clines.tours.dao

import de.clines.tours.model.Tour
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface TourRepository: ReactiveMongoRepository<Tour, String> {

    fun findAllByUsername(username: String): Flux<Tour>

    fun findAllByUsernameAndId(username: String, id: String): Mono<Tour>
}