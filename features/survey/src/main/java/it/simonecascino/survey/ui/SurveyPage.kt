package it.simonecascino.survey.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import it.simonecascino.survey.R
import it.simonecascino.theme.AppDimensions
import it.simonecascino.utils.PresentationState

@Composable
internal fun SurveyPage(
    modifier: Modifier = Modifier,
    text: String,
    answer: String,
    submissionStatus: PresentationState,
    onAnswerTyped: (String) -> Unit,
    submit: (String) -> Unit
){

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall
        )

        TextField(
            modifier = modifier.fillMaxWidth().padding(top = AppDimensions.u100),
            readOnly = submissionStatus == PresentationState.Success,
            label = {
                Text(text = stringResource(id = R.string.survey_field_label))
            },
            value = answer,
            onValueChange = onAnswerTyped
        )
        
        Spacer(modifier = Modifier.weight(1f))

        Crossfade(
            targetState = submissionStatus,
            label = ""
        ) {
            when(it){
                PresentationState.Loading -> Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
                PresentationState.Success -> Spacer(Modifier)
                else -> Button(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = answer.isNotBlank(),
                    onClick = { submit(answer) }
                ) {
                    Text(text = stringResource(id = R.string.survey_submit))
                }
            }
        }

    }

}