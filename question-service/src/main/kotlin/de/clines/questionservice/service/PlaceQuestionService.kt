package de.clines.questionservice.service

import de.clines.questionservice.dto.PlaceDetailsDto
import de.clines.questionservice.model.Question
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodyOrNull
import org.springframework.web.reactive.function.client.awaitExchange

@Service
class PlaceQuestionService(
        private val placeServiceWebClient: WebClient,
        private val userTokenService: UserTokenService
        ) {
    private val sentences = listOf(
            "Would you like to visit %s?",
            "Do you find %s interesting?",
            "What do you think of %s?"
            )

    suspend fun getPlaceQuestion(placeId: String): Question.PlaceQuestion? {
        val placeDetails = placeServiceWebClient
                .get()
                .uri("/details/${placeId}")
                .header("Authorization", "Bearer ${userTokenService.getUserAccessToken()}")
                .accept(MediaType.APPLICATION_JSON)
                .awaitExchange()
                .awaitBodyOrNull<PlaceDetailsDto>() ?: return null
        return with(placeDetails) {
            Question.PlaceQuestion(
                    id = id,
                    name = name,
                    text = sentences.random().format(name),
                    photos = photos,
                    tags = labels,
                    comments = comments,
                    rating = rating,
                    location = location
            )
        }
    }
}