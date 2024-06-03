package it.simonecascino.utils

sealed interface PresentationState {
    data object Idle: PresentationState
    data object Loading: PresentationState
    data class Error(val reason: String?): PresentationState
    data object Success: PresentationState
}