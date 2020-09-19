package it.laface.team.presentation

import it.laface.base.CallState
import it.laface.common.ContentToShow

fun <T> toContent(callState: CallState<T>): ContentToShow<T> =
    when (callState) {
        is CallState.Success -> ContentToShow.Success(callState.result)
        is CallState.Error -> ContentToShow.Error
        CallState.NotStarted, CallState.InProgress -> ContentToShow.Loading
    }
