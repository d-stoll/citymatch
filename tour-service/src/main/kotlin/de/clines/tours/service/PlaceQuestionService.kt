package de.clines.tours.service

import de.clines.tours.dao.TourRepository
import de.clines.tours.dto.PlaceRequestDto
import de.clines.tours.model.Location
import de.clines.tours.model.PlaceQuestionModel
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Service
import reactor.core.publisher.EmitterProcessor

@Service
class PlaceQuestionService(
        private val placeRequestEmitter: EmitterProcessor<PlaceRequestDto>,
        private val tourRepository: TourRepository
        ) {

    fun requestPlaces(tourId: String, location: Location) {
        placeRequestEmitter.onNext(PlaceRequestDto(tourId, location))
    }

    suspend fun addPlaceQuestions(tourId: String, placeQuestionModels: List<PlaceQuestionModel>) {
        val tour = tourRepository.findById(tourId).awaitFirstOrNull() ?: return
        tour.placeQuestions.addAll(placeQuestionModels)
        tourRepository.save(tour).awaitFirstOrNull()
    }



}