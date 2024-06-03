package it.simonecascino.survey_data.models

import kotlinx.serialization.Serializable

@Serializable
data class SubmitRequest(
    val id: Int,
    val answer: String
)
