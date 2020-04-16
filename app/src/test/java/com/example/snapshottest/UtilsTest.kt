package com.example.snapshottest

import org.junit.Assert
import org.junit.Test

class UtilsTest {

    @Test
    fun `plus should be right!`() {
        val result = Utils().plus(1, 2)

        Assert.assertEquals(3, result)
    }

}