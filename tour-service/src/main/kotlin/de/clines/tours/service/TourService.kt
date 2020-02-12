package de.clines.tours.service

import de.clines.tours.dao.TourRepository
import de.clines.tours.dto.*
import de.clines.tours.model.Tour
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TourService(
        private val tourRepository: TourRepository,
        private val generalQuestionService: GeneralQuestionService,
        private val placeQuestionService: PlaceQuestionService,
        private val userModelService: UserModelService,
        private val usernameService: UsernameService
) {

    suspend fun getAllTours() = tourRepository.findAllByUsername(usernameService.getUsername()).asFlow()

    suspend fun getTour(tourId: String): Tour? {
        return tourRepository.findAllByUsernameAndId(usernameService.getUsername(), tourId).awaitSingle()
    }

    @Transactional
    suspend fun createTour(createTourDto: CreateTourDto): Tour {
        val generalQuestionModels = generalQuestionService.getGeneralQuestions(5).toMutableList()
        val tour = Tour(
                name = createTourDto.name,
                username = usernameService.getUsername(),
                time = createTourDto.time,
                location = createTourDto.location,
                createdAt = LocalDateTime.now(),
                finished = false,
                nextQuestionId = generalQuestionModels.first().id,
                userModel = userModelService.defaultUserModel(),
                placeQuestions = mutableListOf(),
                generalQuestions = generalQuestionModels.toMutableList()
        )
        placeQuestionService.requestPlaces(tour.id, tour.location)
        return tourRepository
                .save(tour)
                .awaitSingle()
    }

    @Transactional
    suspend fun updateTour(tourId: String, updateTourDto: UpdateTourDto): Tour? {
        val tour = tourRepository.findAllByUsernameAndId(usernameService.getUsername(), tourId).awaitFirstOrNull()
                ?: return null
        tour.apply {
            updateTourDto.name?.let { name = it }
        }
        return tourRepository.save(tour).awaitFirstOrNull()
    }

    @Transactional
    suspend fun deleteTour(tourId: String): Tour? {
        val tour = tourRepository.findAllByUsernameAndId(usernameService.getUsername(), tourId).awaitFirstOrNull()
                ?: return null
        return tourRepository.delete(tour).thenReturn(tour).awaitSingle()
    }

}