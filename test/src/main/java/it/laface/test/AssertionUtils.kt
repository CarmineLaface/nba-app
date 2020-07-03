package it.laface.test

import org.junit.Assert

inline fun <reified T> Any.shouldBe() {
    Assert.assertTrue(this is T)
}

infix fun <T> T.shouldBe(expected: T) {
    Assert.assertEquals(expected, this)
}
