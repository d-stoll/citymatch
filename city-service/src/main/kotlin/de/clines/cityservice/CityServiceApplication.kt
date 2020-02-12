package de.clines.cityservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CityServiceApplication

fun main(args: Array<String>) {
	runApplication<CityServiceApplication>(*args)
}
