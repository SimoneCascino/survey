package it.simonecascino.survey_data.usecases

import it.simonecascino.survey_domain.repository.SurveyRepository
import it.simonecascino.survey_domain.usecases.SubmitAnswerUseCase
import javax.inject.Inject

class SubmitAnswerUseCaseImpl @Inject constructor(
    private val repository: SurveyRepository
): SubmitAnswerUseCase {
    override suspend fun invoke(id: Int, answer: String) = repository.submitAnswer(id, answer)
}