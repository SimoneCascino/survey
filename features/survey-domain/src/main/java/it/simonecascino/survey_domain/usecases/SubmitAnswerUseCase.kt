package it.simonecascino.survey_domain.usecases

import it.simonecascino.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface SubmitAnswerUseCase {

    suspend operator fun invoke(id: Int, answer: String): Flow<ApiResponse<Unit>>

}