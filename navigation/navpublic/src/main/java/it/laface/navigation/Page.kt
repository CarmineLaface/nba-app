package it.laface.navigation

data class Page(
    val actionResId: Int,
    val arguments: Pair<String, Any?>? = null,
    val tag: String? = null
)
