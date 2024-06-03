package it.simonecascino.survey_data.usecases

import it.simonecascino.survey_data.fakes.FakeSurveyRepository
import it.simonecascino.survey_domain.models.SurveyDomain
import it.simonecascino.survey_domain.usecases.GetSurveyUseCase
import it.simonecascino.test.MainDispatcherRule
import it.simonecascino.utils.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.advanceTimeBy
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
class GetSurveyUseCaseTest{

    private lateinit var getSurveyUseCase: GetSurveyUseCase

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup(){
        getSurveyUseCase = GetSurveyUseCaseImpl(FakeSurveyRepository())
    }

    @Test
    fun `calling get survey returns loading state and then success`() = runTest{

        var result: ApiResponse<List<SurveyDomain>> = ApiResponse.Idle

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            getSurveyUseCase().collect{
                result = it
            }
        }

        assertThat(result).isEqualTo(ApiResponse.Loading)

        advanceTimeBy(3.seconds)

        assertThat(result).isInstanceOf(ApiResponse.Success::class.java)

    }



}