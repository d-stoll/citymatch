package de.clines.questionservice.service

import de.clines.questionservice.model.Question
import org.springframework.stereotype.Service

@Service
class QuestionService(
        private val generalQuestionService: GeneralQuestionService,
        private val placeQuestionService: PlaceQuestionService
) {

    suspend fun getQuestion(questionId: String): Question? {
        return when(questionId.split("-")[0]) {
            "general" -> {
                generalQuestionService.findGeneralQuestion(questionId.replace("general-", ""))
            }
            "place" -> {
                placeQuestionService.getPlaceQuestion(questionId.replace("place-", ""))
            }
            else -> null
        }
    }

}