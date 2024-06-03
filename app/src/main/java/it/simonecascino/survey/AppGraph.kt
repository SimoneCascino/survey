package it.simonecascino.survey

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import it.simonecascino.architecture.injection.composableVM
import it.simonecascino.architecture.viewmodel.NoEvent
import it.simonecascino.destination.HomeGraph
import it.simonecascino.destination.SurveyGraph
import it.simonecascino.destinationbuilder.annotations.Graph
import it.simonecascino.home.HomeScreen
import it.simonecascino.survey.data.SurveyState
import it.simonecascino.survey.data.SurveyViewModel
import it.simonecascino.survey.ui.SurveyScreen

@Graph(
    graphs = [
        HomeGraph::class,
        SurveyGraph::class
    ]
)
@Composable
fun AppGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
){

    NavHost(
        modifier = modifier
            .safeDrawingPadding(),
        navController = navController,
        startDestination = HomeGraph.HomeScreen.route()
    ){

        composable(HomeGraph.HomeScreen.route()){

            HomeScreen {
                navController.navigate(SurveyGraph.SurveyScreen.buildPath())
            }

        }

        composableVM<SurveyState, NoEvent, SurveyViewModel>(
            route = SurveyGraph.SurveyScreen.route()
        ){

            SurveyScreen(
                presentationState = it.presentationState,
                questions = it.questions,
                retry = {
                    downloadSurvey()
                },
                onAnswerTyped = { id, text ->
                    typeAnswer(id, text)
                },
                submit = { id, answer ->
                    submit(id, answer)
                }
            )

        }




    }

}