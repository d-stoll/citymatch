package de.clines.tours.service

import de.clines.tours.dao.TourRepository
import de.clines.tours.model.Tour
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class RecommendationService(
        private val tourRepository: TourRepository,
        private val userModelService: UserModelService,
        private val usernameService: UsernameService
) {

    @Transactional
    suspend fun addAnswerAndRecommendNext(tourId: String, questionId: String, answer: Boolean): Tour? {
        val tour = tourRepository.findAllByUsernameAndId(usernameService.getUsername(), tourId)
                .awaitFirstOrNull() ?: return null
        if(tour.finished || questionId != tour.nextQuestionId) throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        if(questionId.startsWith("general")) {
            val answeredQuestion = tour.generalQuestions.find{ it.id == questionId } ?: return null
            tour.generalQuestions.remove(answeredQuestion).toString()
            userModelService.adaptUserModel(answeredQuestion.tags, tour.userModel, answer)
            if(tour.generalQuestions.isNotEmpty()) {
                tour.nextQuestionId = tour.generalQuestions.first().id
                return tourRepository.save(tour).awaitFirstOrNull()
            }
        } else {
            if(answer) {
                tour.route.add(questionId.substring(6))

                if(tour.time / 60 < tour.route.size) {
                    tour.nextQuestionId = null
                    tour.finished = true
                    return tourRepository.save(tour).awaitFirstOrNull()
                }
            }

            val answeredQuestion = tour.placeQuestions.find { it.id == questionId } ?: return null
            tour.placeQuestions.remove(answeredQuestion)
            userModelService.adaptUserModel(answeredQuestion.tags, tour.userModel, answer)
            if(tour.placeQuestions.isEmpty()) {
                tour.nextQuestionId = null
                tour.finished = true
                return tourRepository.save(tour).awaitFirstOrNull()
            }
        }
        tour.nextQuestionId = userModelService.computeNextPlaceQuestion(tour.placeQuestions,
                tour.userModel) ?: return null
        return tourRepository.save(tour).awaitFirstOrNull()
    }
}