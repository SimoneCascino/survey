package it.simonecascino.survey_data.usecases

import it.simonecascino.survey_domain.models.SurveyDomain
import it.simonecascino.survey_domain.repository.SurveyRepository
import it.simonecascino.survey_domain.usecases.GetSurveyUseCase
import it.simonecascino.utils.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSurveyUseCaseImpl @Inject constructor(
    private val surveyRepository: SurveyRepository
): GetSurveyUseCase {
    override suspend fun invoke(): Flow<ApiResponse<List<SurveyDomain>>> =
        surveyRepository.getSurvey()
}