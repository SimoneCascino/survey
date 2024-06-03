package it.simonecascino.survey_data.models

import kotlinx.serialization.Serializable

@Serializable
data class SurveyResponse(
    val id: Int,
    val question: String
)