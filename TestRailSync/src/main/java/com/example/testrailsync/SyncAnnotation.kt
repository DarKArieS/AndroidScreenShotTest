package com.example.testrailsync

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SyncAnnotation(val caseId: Int, val testDesc: String)

