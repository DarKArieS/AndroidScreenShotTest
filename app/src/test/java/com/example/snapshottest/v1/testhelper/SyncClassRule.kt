package com.example.snapshottest.v1.testhelper

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class SyncClassRule : TestRule {

    private val throwableCollection = mutableListOf<Throwable>()

    fun addThrowable(throwable: Throwable) {
        throwableCollection.add(throwable)
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {

                base.evaluate()

                if (description.isSuite) {
                    val syncAnnotation = getSyncAnnotation(description)
                    if (syncAnnotation != null) {
                        val desc = syncAnnotation.testDesc
                        val id = syncAnnotation.caseId
                        println("${description.className}: all test done, test description update to case: $id!")

                        if (throwableCollection.isNotEmpty()) {
                            println("${description.className}: ${throwableCollection.size} tests are failed, test state update to case: $id!")
                        } else {
                            println("${description.className}: all tests are passed, test state update to case: $id!")
                        }
                    }
                }
            }
        }
    }

    fun getSyncAnnotation(description: Description): SyncAnnotation? {
        val result = description.annotations.find { it.annotationClass == SyncAnnotation::class }
        return if (result != null) {
            result as SyncAnnotation
        } else {
            null
        }
    }
}