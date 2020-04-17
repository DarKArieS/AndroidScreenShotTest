package com.example.testrailsync

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class SyncClassRule : TestRule {

    private val testCases = mutableListOf<TestCase>()

    fun addTestCase(testCase: TestCase) {
        testCases.add(testCase)
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                base.evaluate()

                // Output TestCase
                println("Output TestCase")
                println(testCases.toString())
            }
        }

    }
}