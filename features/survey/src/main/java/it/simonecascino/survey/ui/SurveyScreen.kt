package it.simonecascino.survey.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.util.fastSumBy
import it.simonecascino.destinationbuilder.annotations.Destination
import it.simonecascino.survey.R
import it.simonecascino.survey.data.models.Question
import it.simonecascino.survey.data.models.SubmitResultBoxState
import it.simonecascino.theme.AppDimensions
import it.simonecascino.utils.DoNothing
import it.simonecascino.utils.PresentationState
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Destination(
    graphName = "SurveyGraph"
)
@Composable
fun SurveyScreen(
    modifier: Modifier = Modifier,
    presentationState: PresentationState,
    questions: List<Question>,
    retry: () -> Unit,
    onAnswerTyped: (Int, String) -> Unit,
    submit: (Int, String) -> Unit
){

    Crossfade(targetState = presentationState, label = "") {
        when(it){

            is PresentationState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(text = stringResource(id = R.string.survey_error))

                    Spacer(modifier = Modifier.size(AppDimensions.u100))

                    Button(
                        onClick = {
                            retry()
                        }
                    ) {
                        Text(text = stringResource(id = R.string.survey_retry))
                    }

                }
            }

            PresentationState.Idle -> DoNothing

            PresentationState.Loading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            PresentationState.Success -> {

                if(questions.isEmpty()){
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                             Text(text = stringResource(id = R.string.survey_empty))
                    }
                } else {
                    val pagerState = rememberPagerState {
                        questions.size
                    }

                    val scope = rememberCoroutineScope()

                    Column(
                        modifier = modifier.fillMaxSize()
                    ) {

                        SurveyHeader(
                            currentPage = pagerState.currentPage + 1,
                            total = pagerState.pageCount,
                            submittedCount = questions.sumOf {
                                when(it.submitStatus){
                                    PresentationState.Success -> 1
                                    else -> 0
                                }.toInt()
                            },
                            goNext = {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        pagerState.currentPage +1
                                    )
                                }
                            },
                            goBack = {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        pagerState.currentPage -1
                                    )
                                }
                            }
                        )

                        Box {

                            HorizontalPager(
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(AppDimensions.u100),
                                pageSpacing = AppDimensions.u100,
                                state = pagerState,
                                key = {
                                    questions[it].id
                                }
                            ) {index ->

                                val question = questions[index]

                                SurveyPage(
                                    text = question.text,
                                    answer = question.answer,
                                    submissionStatus = question.submitStatus,
                                    onAnswerTyped = {
                                        onAnswerTyped(question.id, it)
                                    },
                                    submit = {
                                        submit(question.id, it)
                                    }
                                )

                            }

                            val question = questions[pagerState.currentPage]

                            if(question.submitStatus != PresentationState.Idle){
                                val animatedValues by animateFloatAsState(targetValue = if (question.resultBoxState == SubmitResultBoxState.Displaying) 1f else 0f,
                                    label = ""
                                )

                                val isSuccess = question.submitStatus == PresentationState.Success

                                val color = if(isSuccess)
                                    Color.Green
                                else MaterialTheme.colorScheme.error

                                val textColor = if(isSuccess)
                                    Color.Black
                                else Color.White

                                val text = stringResource(
                                    id = if(isSuccess)
                                        R.string.survey_submit_success
                                    else R.string.survey_submit_failure
                                )

                                SurveyResultBox(
                                    animatedValues = animatedValues,
                                    color = color,
                                    textColor = textColor,
                                    text = text,
                                    isSuccess = isSuccess
                                ) {
                                    submit(question.id, question.answer)
                                }
                            }
                            }
                    }
                }
            }
        }
    }
}