package it.laface.common.util

val String.capitalize: String
    get() = if (isNotEmpty()) {
        replaceFirstChar { if (it.isLowerCase()) it.uppercaseChar() else it }
    } else {
        this
    }
