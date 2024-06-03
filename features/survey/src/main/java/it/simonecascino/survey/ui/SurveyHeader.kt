package it.simonecascino.survey.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import it.simonecascino.survey.R
import it.simonecascino.theme.AppDimensions

@Composable
internal fun SurveyHeader(
    modifier: Modifier = Modifier,
    currentPage: Int,
    total: Int,
    submittedCount: Int,
    goNext: () -> Unit,
    goBack: () -> Unit
){

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.padding(AppDimensions.u100),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.survey_question_header, currentPage, total),
                style = MaterialTheme.typography.headlineMedium
            )

            TextButton(
                onClick = goBack,
                enabled = currentPage > 1
            ) {
                Text(text = stringResource(id = R.string.survey_button_previous))
            }

            TextButton(
                onClick = goNext,
                enabled = currentPage < total
            ) {
                Text(text = stringResource(id = R.string.survey_button_next))
            }
            
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(AppDimensions.u100),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = stringResource(id = R.string.survey_submitted, submittedCount),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onTertiary
                )
            )

        }

    }

}