package it.laface.test

import org.junit.Assert

infix fun <T> T.shouldBe(expected: T) {
    Assert.assertEquals(expected, this)
}
