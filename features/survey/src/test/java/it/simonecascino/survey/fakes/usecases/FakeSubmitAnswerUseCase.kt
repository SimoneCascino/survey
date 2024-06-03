package it.simonecascino.survey.fakes.usecases

import it.simonecascino.survey_domain.usecases.SubmitAnswerUseCase
import it.simonecascino.utils.ApiResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

class FakeSubmitAnswerUseCase: SubmitAnswerUseCase {
    override suspend fun invoke(id: Int, answer: String) = flow {
        emit(ApiResponse.Loading)
        delay(2.seconds)
        emit(ApiResponse.Success(Unit))
    }
}