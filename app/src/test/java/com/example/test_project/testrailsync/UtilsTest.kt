package com.example.test_project.testrailsync

import com.example.test_project.Utils
import com.example.testrailsync.SyncAnnotation
import com.example.testrailsync.SyncClassRule
import com.example.testrailsync.SyncCollector
import org.junit.Assert
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test

class UtilsTest {

    @get:Rule
    var collector = SyncCollector(syncClassRule)

    companion object {
        @ClassRule
        @JvmField
        var syncClassRule = SyncClassRule()
    }

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