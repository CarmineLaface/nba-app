package it.laface.common.navigation

sealed class EventType {

    data class Navigation(val type: NavigationType) : EventType()
    data class SnackBar(val info: SnackbarInfo) : EventType()
}
