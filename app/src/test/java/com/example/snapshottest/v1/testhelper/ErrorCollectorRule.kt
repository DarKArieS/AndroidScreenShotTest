package com.example.snapshottest.v1.testhelper

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ErrorCollectorRule(val classRule: SyncClassRule) : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    base.evaluate()
                } catch (e: Throwable) {
                    classRule.addThrowable(e)
                    throw e
                }
            }
        }
    }
}