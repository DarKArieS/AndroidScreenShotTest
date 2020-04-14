package com.example.snapshottest

import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import com.facebook.testing.screenshot.ScreenshotRunner
import com.facebook.testing.screenshot.ScreenshotRunner.onCreate


class CustomRunner : AndroidJUnitRunner() {
    override fun onCreate(args: Bundle) {
        onCreate(this, args)
        super.onCreate(args)
    }

    override fun finish(resultCode: Int, results: Bundle) {
        ScreenshotRunner.onDestroy()
        super.finish(resultCode, results)
    }
}