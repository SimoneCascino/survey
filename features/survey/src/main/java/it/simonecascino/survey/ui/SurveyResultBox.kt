package it.simonecascino.survey.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import it.simonecascino.survey.R
import it.simonecascino.theme.AppDimensions
import it.simonecascino.theme.AppTheme

@Composable
internal fun SurveyResultBox(
    animatedValues: Float,
    color: Color,
    textColor: Color,
    text: String,
    isSuccess: Boolean,
    submit: () -> Unit
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .scale(animatedValues)
            .alpha(animatedValues)
            .background(color)
            .padding(
                horizontal = AppDimensions.u100,
                vertical = AppDimensions.u400
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            )
        }

        if(isSuccess)
            Spacer(modifier = Modifier.weight(1F))
        else Box(
            modifier = Modifier
                .weight(1F)
                .align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center
        ){
            ElevatedButton(
                onClick = submit
            ) {
                Text(text = stringResource(id = R.string.survey_retry))
            }
        }

    }

}

@Preview
@Composable
private fun SurveyResultBoxPreview(){

    AppTheme {

        Column(modifier = Modifier.background(Color.White
        )){
            SurveyResultBox(animatedValues = 1F, color = Color.Green, textColor = Color.Black, text = "Success", isSuccess = true) {

            }
        }

    }

}