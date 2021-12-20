package it.laface.test

import org.mockito.Mockito
import org.mockito.stubbing.Answer
import org.mockito.stubbing.OngoingStubbing

inline fun <reified T : Any> mock(): T {
    return Mockito.mock(T::class.java)
}

inline fun <reified T : Any> mock(stubbing: T.() -> Unit): T {
    return mock<T>().apply { stubbing() }
}

@Suppress("NOTHING_TO_INLINE")
inline fun <T> whenever(methodCall: T): OngoingStubbing<T> {
    return Mockito.`when`(methodCall)
}

@Suppress("NOTHING_TO_INLINE")
inline infix fun <T> OngoingStubbing<T>.thenAnswer(answer: Answer<T>): OngoingStubbing<T> {
    return thenAnswer(answer)
}
