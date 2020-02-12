package de.clines.documentation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class DocumentationApplication

fun main(args: Array<String>) {
	runApplication<DocumentationApplication>(*args)
}
