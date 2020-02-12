package de.clines.tours.controller

import de.clines.tours.dto.*
import de.clines.tours.model.Tour
import de.clines.tours.service.RecommendationService
import de.clines.tours.service.TourService
import io.swagger.annotations.*
import kotlinx.coroutines.flow.toList
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Api(tags = ["Tours"], description = "Endpoints for creating, managing and deleting tours. Keep in mind that users " +
                                    "can only interact with tours, that they own.")
@RestController
class TourController(
        private val tourService: TourService,
        private val recommendationService: RecommendationService
) {

    @ApiOperation("Returns all created tours of the user.", response = TourDto::class,
            responseContainer = "List")
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun getAllTours() = tourService.getAllTours().toList().map(Tour::toDto)

    @ApiOperation("Returns the tour with id=tourId", response = TourDto::class,
            notes = "The tour object represents the current state of the tour. It changes when the next " +
                    "question is answered. The next questions is specified by the nextQuestionId field. " +
                    "Information about the question can be retrieved from the questions-Endpoints. " +
                    "(See docs of the question-service)")
    @GetMapping("/{tourId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun getTour(@PathVariable("tourId") tourId: String): TourDto {
        return tourService.getTour(tourId)?.toDto() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @ApiOperation("Creates a new tour based on the specified location.", response = TourDto::class,
            notes = "A tour object is created and returned based on the specified location. The tour creation " +
                    "may take a few seconds to complete. For more information about the tour object and how it " +
                    "behaves, see the notes on the GET /{tourId} endpoint.")
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(code = HttpStatus.CREATED)
    suspend fun createTour(@RequestBody createTourDto: CreateTourDto): TourDto {
        return tourService.createTour(createTourDto).toDto()
    }

    @ApiOperation("Updates a tour based with the given values.", response = TourDto::class,
            notes = "The tour object with the specified id is update and returned with the new values.")
    @PutMapping("/{tourId}",consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun updateTour(@PathVariable("tourId") tourId: String, @RequestBody updateTourDto: UpdateTourDto): TourDto {
        return tourService.updateTour(tourId, updateTourDto)?.toDto()
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @ApiOperation("Deletes the tour with the specified id.")
    @DeleteMapping("/{tourId}")
    suspend fun deleteTour(@PathVariable("tourId") tourId: String) {
        tourService.deleteTour(tourId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @ApiOperation("Adds an answer to the tour with id=tourId", response = TourDto::class,
            notes = "You can add an anwser to the current active question of a tour via this endpoint. The current " +
                    "active question is specificied by the nextQuestionId field of the tour object")
    @PostMapping("/{tourId}/answer", produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun addAnswer(@PathVariable("tourId") tourId: String, @RequestBody answerDto: AnswerDto): TourDto {
        return recommendationService.addAnswerAndRecommendNext(tourId, answerDto.questionId, answerDto.answer)?.toDto()
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @ApiOperation("Gets the route of the tour with id=tourId", response = String::class, responseContainer = "List",
            notes = "Returns a list of ids of places, which the user wanted to see in this tour. The ids reference " +
                    "places in Google Maps")
    @GetMapping("/{tourId}/route", produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun getRoute(@PathVariable("tourId") tourId: String): List<String> {
        return tourService.getTour(tourId)?.route ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

}