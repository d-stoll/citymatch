package de.clines.tours.dto

import de.clines.tours.model.PlaceQuestionModel
import de.clines.tours.model.Location

data class PlaceDto(
        val id: String,
        val name: String,
        val location: Location,
        val rating: Float,
        val userRatingsTotal: Int,
        val labels: List<String>
) {

    fun toPlaceQuestionModel() = PlaceQuestionModel("place-$id", name, location, rating, userRatingsTotal, labels)

}