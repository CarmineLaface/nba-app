package it.laface.navigation

data class Page(
    val fragmentClass: Class<*>,
    val arguments: Pair<String, Any?>? = null,
    val tag: String? = null
)