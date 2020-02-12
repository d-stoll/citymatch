package de.clines.tours.service

import de.clines.tours.model.PlaceQuestionModel
import org.springframework.stereotype.Service
import kotlin.math.pow

@Service
class UserModelService {

    fun adaptUserModel(labels: List<String>, userModel: MutableMap<String, Double>, answer: Boolean) {
        userModel.forEach { (label, value) ->
            val target = if (labels.contains(label) == answer) 1.0 else 0.0
            val delta = 0.2 * (target - value)
            userModel[label]= value + delta
        }
    }

    fun defaultUserModel(): MutableMap<String, Double> {
        return mutableMapOf(
                "amusement_park" to 0.5,
                "art_gallery, bar" to 0.5,
                "church" to 0.5,
                "city_hall" to 0.5,
                "mosque" to 0.5,
                "museum" to 0.5,
                "night_club" to 0.5,
                "park" to 0.5,
                "store" to 0.5,
                "synagogue" to 0.5,
                "university" to 0.5,
                "place_of_worship" to 0.5,
                "town_square" to 0.5,
                "political" to 0.5,
                "library" to 0.5,
                "zoo" to 0.5
        )
    }

    fun computeNextPlaceQuestion(placeModels: List<PlaceQuestionModel>, userModel: Map<String, Double>): String? {
        return placeModels
                .map { it.id to (meanSquareError(it.tags, userModel) / (normalizeRatingsTotal(it.userRatingsTotal) / 50) * it.rating.toDouble()) }
                .toMap()
                .minBy { it.value }?.key
    }

    private fun meanSquareError(labels: List<String>, userModel: Map<String, Double>): Double {
        return userModel
                .map { (label, value) -> (value - if (labels.contains(label)) 1.0 else 0.0).pow(2) }
                .reduce(Double::plus) / userModel.keys.size
    }

    private fun normalizeRatingsTotal(ratingsTotal: Int) = if (ratingsTotal > 5000) 5000 else ratingsTotal

}