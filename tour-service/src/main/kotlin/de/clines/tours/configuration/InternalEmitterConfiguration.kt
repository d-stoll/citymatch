package de.clines.tours.configuration

import de.clines.tours.dto.PlaceRequestDto
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.EmitterProcessor

@Configuration
class InternalEmitterConfiguration {

    @Bean
    fun placeRequestEmitter(): EmitterProcessor<PlaceRequestDto> = EmitterProcessor.create()

}