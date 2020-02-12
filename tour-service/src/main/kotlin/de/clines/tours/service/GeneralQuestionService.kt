package de.clines.tours.service

import de.clines.tours.dto.GeneralQuestionModelDto
import de.clines.tours.model.GeneralQuestionModel
import de.clines.tours.properties.ServiceUriProperties
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange

@Service
class GeneralQuestionService(
        private val serviceUriProperties: ServiceUriProperties,
        private val webClient: WebClient,
        private val userTokenService: UserTokenService
) {

    suspend fun getGeneralQuestions(count: Int): List<GeneralQuestionModel> {
        return webClient
                .get()
                .uri("${serviceUriProperties.questionService}?type=general&strategy=random&count=$count")
                .header("Authorization", "Bearer ${userTokenService.getUserAccessToken()}")
                .awaitExchange()
                .awaitBody<List<GeneralQuestionModelDto>>()
                .map(GeneralQuestionModelDto::toGeneralQuestionModel)
    }

}