package com.example.snapshottest.v1

import com.example.snapshottest.Utils
import com.example.snapshottest.v1.testhelper.SyncAnnotation
import com.example.snapshottest.v1.testhelper.SyncRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


class UtilsTest {

    @get:Rule
    var syncRule = SyncRule()

    @Test
    @SyncAnnotation(12345, "hello~")
    fun `plus should be right!`() {
        val result = Utils().plus(1, 2)

        Assert.assertEquals(3, result)
    }

    @Test
    @SyncAnnotation(12346, "hello~")
    fun `plus should be right!2`() {
        val result = Utils().plus(3, 4)

        Assert.assertEquals(8, result)
    }

}