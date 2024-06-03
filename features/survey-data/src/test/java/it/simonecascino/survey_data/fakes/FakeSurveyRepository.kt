package it.simonecascino.survey_data.fakes

import it.simonecascino.survey_domain.models.SurveyDomain
import it.simonecascino.survey_domain.repository.SurveyRepository
import it.simonecascino.utils.ApiResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

class FakeSurveyRepository: SurveyRepository {

    override suspend fun getSurvey(): Flow<ApiResponse<List<SurveyDomain>>> = flow{
        emit(ApiResponse.Loading)

        delay(2.seconds)

        emit(ApiResponse.Success(listOf(SurveyDomain(
            1, "What is your favourite colour?"
        ))))
    }

    override suspend fun submitAnswer(id: Int, answer: String): Flow<ApiResponse<Unit>> = flow{

        emit(ApiResponse.Loading)

        delay(2.seconds)

        emit(ApiResponse.Success(Unit))

    }
}