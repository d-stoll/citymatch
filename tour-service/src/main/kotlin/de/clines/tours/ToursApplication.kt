package de.clines.tours

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ToursApplication

fun main(args: Array<String>) {
	runApplication<ToursApplication>(*args)
}
