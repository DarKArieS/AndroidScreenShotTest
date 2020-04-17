package com.example.snapshottest.v1.testhelper

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class SyncRule : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    base.evaluate()
                } catch (e: Throwable) {
                    if (description.isTest) {
                        val syncAnnotation = getSyncAnnotation(description)
                        if (syncAnnotation != null) {
                            val id = syncAnnotation.caseId
                            println("${description.methodName}: test is failed, update state to case: $id")
                        }
                    }
                    throw e
                }

                if (description.isTest) {
                    val syncAnnotation = getSyncAnnotation(description)
                    if (syncAnnotation != null) {
                        val desc = syncAnnotation.testDesc
                        val id = syncAnnotation.caseId
                        println("${description.methodName}: test is passed, test description update to case: $id!")
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