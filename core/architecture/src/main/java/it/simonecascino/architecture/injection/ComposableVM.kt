package it.simonecascino.architecture.injection

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import it.simonecascino.architecture.viewmodel.BaseEvent
import it.simonecascino.architecture.viewmodel.BaseState
import it.simonecascino.architecture.viewmodel.BaseViewModel

inline fun <S: BaseState, E: BaseEvent, reified V: BaseViewModel<S, E>> NavGraphBuilder.composableVM(
    route: String,
    noinline enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    noinline exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    noinline popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    noinline popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
    noinline onEventDispatched: ((E) -> Unit)? = null,
    crossinline content: @Composable V.(S) -> Unit
){

    composable(
        route = route,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ){

        val viewModel = hiltViewModel<V>(it)

        EventHandler(onEventDispatched, viewModel.events)

        val state by viewModel.currentState.collectAsStateWithLifecycle(
            lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current
        )
        viewModel.content(state)

    }

}