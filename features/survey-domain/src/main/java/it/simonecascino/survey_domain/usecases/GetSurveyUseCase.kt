package it.simonecascino.survey_domain.usecases

import it.simonecascino.survey_domain.models.SurveyDomain
import it.simonecascino.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface GetSurveyUseCase{
    suspend operator fun invoke(): Flow<ApiResponse<List<SurveyDomain>>>
}