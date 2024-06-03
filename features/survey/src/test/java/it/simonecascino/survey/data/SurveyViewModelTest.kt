package it.simonecascino.survey.data

import com.google.common.truth.Truth.assertThat
import it.simonecascino.survey.fakes.usecases.FakeGetSurveyUseCase
import it.simonecascino.survey.fakes.usecases.FakeSubmitAnswerUseCase
import it.simonecascino.test.MainDispatcherRule
import it.simonecascino.utils.PresentationState
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
class SurveyViewModelTest{

    @get:Rule
    val rule = MainDispatcherRule()

    private lateinit var viewModel: SurveyViewModel

    @Before
    fun setup(){
        viewModel = SurveyViewModel(
            FakeGetSurveyUseCase(),
            FakeSubmitAnswerUseCase()
        )
    }

    @Test
    fun emission() = runTest{

        var surveyState = SurveyState()

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.currentStateForTesting.collect{
                surveyState = it
            }
        }

        assertThat(surveyState.presentationState).isEqualTo(PresentationState.Loading)

        advanceTimeBy(3.seconds)

        assertThat(surveyState.presentationState).isEqualTo(PresentationState.Success)
        assertThat(surveyState.questions).isNotEmpty()


    }

    @Test
    fun `typing an answer the state changes accordingly` () = runTest{

        advanceTimeBy(3.seconds)

        viewModel.typeAnswer(1, "test")

        val questions = viewModel.currentStateForTesting.value.questions

        val question = questions.find { it.id == 1 }

        assertThat(question).isNotNull()
        assertThat(question?.id).isEqualTo(1)
        assertThat(question?.answer).isEqualTo("test")

    }

    @Test
    fun `submit answer`() = runTest{

        advanceTimeBy(3.seconds)

        viewModel.typeAnswer(1, "test")
        viewModel.submit(1, "test")

        advanceTimeBy(3.seconds)

        val questions = viewModel.currentStateForTesting.value.questions
        val question = questions.find { it.id == 1 }

        assertThat(question?.submitStatus).isEqualTo(PresentationState.Success)


    }


}