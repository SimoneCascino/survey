package it.simonecascino.survey_data.repository

import com.google.common.truth.Truth.assertThat
import it.simonecascino.survey_data.fakes.FakeSurveyApi
import it.simonecascino.survey_domain.models.SurveyDomain
import it.simonecascino.survey_domain.repository.SurveyRepository
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
class SurveyRepositoryTest{

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var surveyRepository: SurveyRepository

    @Before
    fun setup(){
        surveyRepository = SurveyRepositoryImpl(FakeSurveyApi())
    }

    @Test
    fun `calling get survey returns loading state and then success`() = runTest{

        var result: ApiResponse<List<SurveyDomain>> = ApiResponse.Idle

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            surveyRepository.getSurvey().collect{
                result = it
            }
        }

        assertThat(result).isEqualTo(ApiResponse.Loading)

        advanceTimeBy(3.seconds)

        assertThat(result).isInstanceOf(ApiResponse.Success::class.java)
        assertThat((result as ApiResponse.Success).result).isNotEmpty()

    }

    @Test
    fun `calling submit answer returns loading state and then success`() = runTest{

        var result: ApiResponse<Unit> = ApiResponse.Idle

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            surveyRepository.submitAnswer(1, "test").collect{
                result = it
            }
        }

        assertThat(result).isEqualTo(ApiResponse.Loading)

        advanceTimeBy(3.seconds)

        assertThat(result).isInstanceOf(ApiResponse.Success::class.java)

    }

}