package it.simonecascino.survey.data

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.simonecascino.architecture.viewmodel.BaseViewModel
import it.simonecascino.architecture.viewmodel.NoEvent
import it.simonecascino.survey.data.models.SubmitResultBoxState
import it.simonecascino.survey.data.models.surveyDomainToQuestions
import it.simonecascino.survey_domain.usecases.GetSurveyUseCase
import it.simonecascino.survey_domain.usecases.SubmitAnswerUseCase
import it.simonecascino.utils.ApiResponse
import it.simonecascino.utils.DoNothing
import it.simonecascino.utils.PresentationState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val getSurveyUseCase: GetSurveyUseCase,
    private val submitAnswerUseCase: SubmitAnswerUseCase
): BaseViewModel<SurveyState, NoEvent>(SurveyState()) {

    init {
        downloadSurvey()
    }

    fun downloadSurvey(){
        viewModelScope.launch {
            getSurveyUseCase().collect{

                when(it){
                    is ApiResponse.Failure -> changeState {
                        copy(
                            presentationState = PresentationState.Error(null)
                        )
                    }
                    ApiResponse.Loading -> changeState {
                        copy(
                            presentationState = PresentationState.Loading
                        )
                    }
                    is ApiResponse.Success -> {
                        changeState {
                            copy(
                                questions = surveyDomainToQuestions(it.result),
                                presentationState = PresentationState.Success
                            )
                        }
                    }
                    ApiResponse.Idle -> DoNothing
                }

            }
        }
    }

    fun typeAnswer(
        id: Int,
        text: String
    ){

        changeState {
            copy(
                questions = questions.map {
                    if(it.id == id)
                        it.copy(
                            answer = text
                        )
                    else it
                }
            )
        }

    }

    fun submit(
        id: Int,
        answer: String
    ){
        viewModelScope.launch {
            submitAnswerUseCase(id, answer).collect{ apiResponse ->

                changeState {
                    copy(
                        questions = questions.map { question ->

                            if(question.id == id){
                                when(apiResponse){
                                    is ApiResponse.Failure -> question.copy(
                                        submitStatus = PresentationState.Error(null),
                                        resultBoxState = SubmitResultBoxState.Displaying
                                    )
                                    ApiResponse.Idle -> question.copy(submitStatus = PresentationState.Idle)
                                    ApiResponse.Loading -> question.copy(submitStatus = PresentationState.Loading)
                                    is ApiResponse.Success -> question.copy(
                                        submitStatus = PresentationState.Success,
                                        resultBoxState = SubmitResultBoxState.Displaying
                                    )
                                }
                            } else question

                        }
                    )
                }

                when(apiResponse){
                    is ApiResponse.Failure,
                    is ApiResponse.Success -> {
                        delay(5.seconds)
                        changeState {
                            copy(
                                questions = questions.map { question ->
                                    question.copy(
                                        resultBoxState = SubmitResultBoxState.Idle
                                    )
                                }
                            )
                        }
                    }
                    else -> DoNothing
                }

            }
        }
    }

}