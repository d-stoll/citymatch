package de.clines.questionservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuestionServiceApplication

fun main(args: Array<String>) {
	runApplication<QuestionServiceApplication>(*args)
}
