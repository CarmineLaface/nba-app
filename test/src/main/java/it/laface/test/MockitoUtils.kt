package it.laface.test

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

inline fun <reified T> mock(): T =
    Mockito.mock(T::class.java)

inline fun <reified T> mock(block: T.() -> Unit): T =
    Mockito.mock(T::class.java).apply { block() }

inline fun <reified T, R> T.on(block: T.() -> R): OngoingStubbing<R> =
    Mockito.`when`(block())

fun <T> whenever(methodCall: T): OngoingStubbing<T> =
    Mockito.`when`(methodCall)

infix fun <T> OngoingStubbing<T>.thenReturn(value: T): OngoingStubbing<T> =
    thenReturn(value)

inline fun <reified T : Any> any(): T =
    Mockito.any(T::class.java) ?: castNull()

@Suppress("UNCHECKED_CAST")
fun <T> castNull(): T = null as T

fun <T> verify(mock: T): T =
    Mockito.verify(mock)

fun <T> verifyNever(mock: T): T =
    Mockito.verify(mock, Mockito.never())
