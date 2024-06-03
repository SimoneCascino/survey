package it.simonecascino.survey.fakes.usecases

import it.simonecascino.survey_domain.models.SurveyDomain
import it.simonecascino.survey_domain.usecases.GetSurveyUseCase
import it.simonecascino.utils.ApiResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

class FakeGetSurveyUseCase: GetSurveyUseCase {
    override suspend fun invoke(): Flow<ApiResponse<List<SurveyDomain>>> = flow {

        emit(ApiResponse.Loading)

        delay(2.seconds)

        emit(ApiResponse.Success(listOf(SurveyDomain(
            1, "Whxat is your favourite colour?"
        ))))

    }
}