package it.simonecascino.survey.data.models

import it.simonecascino.utils.PresentationState

data class Question(
    val id: Int,
    val text: String,
    val answer: String,
    val submitStatus: PresentationState,
    val resultBoxState: SubmitResultBoxState = SubmitResultBoxState.Idle
)

enum class SubmitResultBoxState{
    Idle, Displaying
}
