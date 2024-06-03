package it.simonecascino.survey_domain.repository

import it.simonecascino.survey_domain.models.SurveyDomain
import it.simonecascino.utils.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface SurveyRepository {

    suspend fun getSurvey(): Flow<ApiResponse<List<SurveyDomain>>>

    suspend fun submitAnswer(
        id: Int,
        answer: String
    ): Flow<ApiResponse<Unit>>

}