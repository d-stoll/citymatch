package de.clines.tours.model

data class PlaceQuestionModel(
        val id: String,
        val name: String,
        val location: Location,
        val rating: Float,
        val userRatingsTotal: Int,
        val tags: List<String>
)