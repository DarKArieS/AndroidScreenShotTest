package com.example.snapshottest.v1

import com.example.snapshottest.Utils
import com.example.snapshottest.v1.testhelper.ErrorCollectorRule
import com.example.snapshottest.v1.testhelper.SyncAnnotation
import com.example.snapshottest.v1.testhelper.SyncClassRule
import org.junit.*


@SyncAnnotation(12345, "hello~")
class UtilsTestByClass {

    @get:Rule
    var errorCollectorRule = ErrorCollectorRule(syncClassRule)

    companion object {
        @ClassRule
        @JvmField
        var syncClassRule = SyncClassRule()
    }

    @Test
    fun `plus should be right!`() {
        val result = Utils().plus(1, 2)

        Assert.assertEquals(3, result)
    }

    @Test
    fun `plus should be right!2`() {
        val result = Utils().plus(3, 4)

        Assert.assertEquals(8, result)
    }

}