package de.clines.questionservice.dao

import de.clines.questionservice.model.Question
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface GeneralQuestionRepository: ReactiveMongoRepository<Question.GeneralQuestion, String>