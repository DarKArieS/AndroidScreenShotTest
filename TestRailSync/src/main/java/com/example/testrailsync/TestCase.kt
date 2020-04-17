package com.example.testrailsync

data class TestCase(
    val caseId: Int,
    val testName: String,
    val unitTestDesc: String,
    val isPassed: Boolean = false
)