package com.example.snapshottest.v1.testhelper

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class SyncAnnotation(val caseId: Int, val testDesc: String)

