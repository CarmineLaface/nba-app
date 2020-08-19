package it.laface.common.core

/**
 * this is used to make the when 'statement' a when 'expression'
 * the expression needs to be exhaustive at compile time as opposed to the statement
 */
val <T> T.exhaustive: T
    get() = this

inline fun <T> T.applyIf(predicate: Boolean, block: T.() -> Unit): T {
    if (predicate) apply(block)
    return this
}
