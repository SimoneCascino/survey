package it.simonecascino.architecture.injection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import it.simonecascino.architecture.viewmodel.BaseEvent
import it.simonecascino.architecture.viewmodel.BaseState
import it.simonecascino.architecture.viewmodel.BaseViewModel

@Composable
inline fun <S: BaseState, E: BaseEvent, reified V: BaseViewModel<S, E>> contentVM(
    noinline onEventDispatched: ((E) -> Unit)? = null,
    content: @Composable V.(S) -> Unit
){
    val viewModel = viewModel<V>()
    val state by viewModel.currentState.collectAsStateWithLifecycle()

    EventHandler(onEventDispatched, viewModel.events)

    viewModel.content(state)
}