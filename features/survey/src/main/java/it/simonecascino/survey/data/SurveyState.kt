package it.simonecascino.survey.data

import it.simonecascino.architecture.viewmodel.BaseState
import it.simonecascino.survey.data.models.Question
import it.simonecascino.utils.PresentationState

data class SurveyState(
    val questions: List<Question> = listOf(),
    val presentationState: PresentationState = PresentationState.Idle,
): BaseState