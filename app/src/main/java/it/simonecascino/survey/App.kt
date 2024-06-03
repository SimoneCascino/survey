package it.simonecascino.survey

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import it.simonecascino.destination.AppGraphResolver
import it.simonecascino.destination.HomeGraph
import it.simonecascino.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(){

    AppTheme {

        val navController = rememberNavController()
        val entry by navController.currentBackStackEntryAsState()
        val currentRoute = entry?.destination?.route ?: HomeGraph.HomeScreen.route()

        val destination = AppGraphResolver.resolve(currentRoute)

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(id = it.simonecascino.survey.R.string.app_name))
                    },
                    navigationIcon = {

                        if(destination != HomeGraph.HomeScreen){
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                            }
                        }

                    }
                )
            }
        ) {
            AppGraph(
                modifier = Modifier
                    .padding(it)
                    .consumeWindowInsets(it),
                navController = navController
            )
        }

    }

}