package com.example.testrailsync


import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class SyncCollector(val syncClassRule: SyncClassRule) : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    base.evaluate()
                } catch (e: Throwable) {
                    getTestCase(description, false)?.apply {
                        syncClassRule.addTestCase(this)
                    }
                    throw e
                }

                getTestCase(description, true)?.apply {
                    syncClassRule.addTestCase(this)
                }
            }
        }
    }

    fun getTestCase(description: Description, isPassed: Boolean): TestCase? {
        val result = description.annotations.find { it.annotationClass == SyncAnnotation::class }
        return if (result != null) {
            val id = (result as SyncAnnotation).caseId
            val name = description.methodName
            val desc = (result as SyncAnnotation).testDesc

            return TestCase(id, name, desc, isPassed)
        } else {
            null
        }
    }
}