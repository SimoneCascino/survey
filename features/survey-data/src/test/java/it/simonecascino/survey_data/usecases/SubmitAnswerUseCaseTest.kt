package it.simonecascino.survey_data.usecases

import com.google.common.truth.Truth
import it.simonecascino.survey_data.fakes.FakeSurveyRepository
import it.simonecascino.survey_domain.models.SurveyDomain
import it.simonecascino.survey_domain.usecases.SubmitAnswerUseCase
import it.simonecascino.test.MainDispatcherRule
import it.simonecascino.utils.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
class SubmitAnswerUseCaseTest {

    private lateinit var submitAnswerUseCase: SubmitAnswerUseCase

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup(){
        submitAnswerUseCase = SubmitAnswerUseCaseImpl(FakeSurveyRepository())
    }

    @Test
    fun `calling submit answer triggers the repository flow accordingly`() = runTest{

        var result: ApiResponse<Unit> = ApiResponse.Idle

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            submitAnswerUseCase(id = 1, answer = "tet").collect{
                result = it
            }
        }

        Truth.assertThat(result).isEqualTo(ApiResponse.Loading)

        advanceTimeBy(3.seconds)

        Truth.assertThat(result).isInstanceOf(ApiResponse.Success::class.java)

    }

}