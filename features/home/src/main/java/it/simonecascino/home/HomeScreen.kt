package it.simonecascino.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import it.simonecascino.components.StartButton
import it.simonecascino.destinationbuilder.annotations.Destination
import it.simonecascino.theme.AppTheme


@Destination(
    graphName = "HomeGraph"
)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onStartSurveyClicked: () -> Unit
){

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        StartButton(
            text = stringResource(id = R.string.button_start_survey),
            onClick = onStartSurveyClicked
        )

    }

}

@Composable
@Preview
private fun HomeScreenPreview(){

    AppTheme {

        HomeScreen {

        }

    }

}