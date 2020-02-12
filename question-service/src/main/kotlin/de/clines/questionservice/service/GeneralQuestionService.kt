package de.clines.questionservice.service

import de.clines.questionservice.dao.GeneralQuestionRepository
import de.clines.questionservice.model.Question
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Service

@Service
class GeneralQuestionService(
        private val generalQuestionRepository: GeneralQuestionRepository
) {

    suspend fun findGeneralQuestion(questionId: String): Question.GeneralQuestion? {
        return generalQuestionRepository.findById(questionId).awaitFirstOrNull()
    }

    suspend fun findAllGeneralQuestions(): Flow<Question.GeneralQuestion> {
        return generalQuestionRepository.findAll().asFlow()
    }

}