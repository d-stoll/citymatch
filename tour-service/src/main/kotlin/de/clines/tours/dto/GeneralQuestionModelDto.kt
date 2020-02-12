package de.clines.tours.dto

import de.clines.tours.model.GeneralQuestionModel

data class GeneralQuestionModelDto(
        val id: String,
        val tags: List<String>
) {

    fun toGeneralQuestionModel() = GeneralQuestionModel("general-$id", tags)

}