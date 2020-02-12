package de.clines.tours.configuration

import de.clines.tours.dto.PlaceDto
import de.clines.tours.dto.PlaceReplyDto
import de.clines.tours.dto.PlaceRequestDto
import de.clines.tours.service.PlaceQuestionService
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.asFlux
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.EmitterProcessor
import reactor.core.publisher.Flux
import java.util.function.Consumer
import java.util.function.Supplier

@Configuration
class MessageBrokerConfiguration(
        private val placeRequestEmitter: EmitterProcessor<PlaceRequestDto>,
        private val placeQuestionService: PlaceQuestionService
) {

    @Bean
    fun placeRequestSource(): Supplier<Flux<PlaceRequestDto>> = Supplier { placeRequestEmitter }

    @Bean
    fun placeReplySink(): Consumer<Flux<PlaceReplyDto>> = Consumer { flux ->
        flux
                .log()
                .asFlow()
                .onEach {
                    placeQuestionService.addPlaceQuestions(it.tourId, it.places.map(PlaceDto::toPlaceQuestionModel))
                }
                .asFlux()
                .subscribe()
    }

}