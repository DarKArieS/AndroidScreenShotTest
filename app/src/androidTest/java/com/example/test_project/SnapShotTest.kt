package com.example.test_project

import android.Manifest
import android.view.LayoutInflater
import android.view.View
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import com.facebook.testing.screenshot.Screenshot
import com.facebook.testing.screenshot.ScreenshotRunner
import com.facebook.testing.screenshot.ViewHelpers
import com.facebook.testing.screenshot.internal.Registry
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.io.File

class SnapShotTest {

    @get:Rule
    val permission = GrantPermissionRule.grant(
        *getPermission()
    )!!

    @Test
    fun mkdir() {
        val mContext = InstrumentationRegistry.getInstrumentation().targetContext
        val externalStorage = System.getenv("EXTERNAL_STORAGE")
        val mArguments = Registry.getRegistry().arguments
        val DEFAULT_SDCARD_DIRECTORY = "screenshots"

        val sdcardDirectory =
            if (mArguments.containsKey(ScreenshotRunner.SDCARD_DIRECTORY)) mArguments.getString(
                ScreenshotRunner.SDCARD_DIRECTORY
            ) else DEFAULT_SDCARD_DIRECTORY

        val parent = String.format(
            "%s/%s/%s/",
            externalStorage,
            sdcardDirectory,
            mContext.packageName
        )

        val child = String.format("%s/screenshots-%s", parent, "XDD")

        File(parent).mkdirs()

        val dir = File(child)
        dir.mkdir()

        Assert.assertTrue(dir.exists())

    }

    @Test
    @Throws(Throwable::class)
    fun doScreenshot() {
        //val targetContext = ApplicationProvider.getApplicationContext<Context>()
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        val mLayoutInflater = LayoutInflater.from(targetContext)

        /**
         * Create and set up your view some how. This might be inflating,
         * or creating from a view class. You might want to set properties
         * on the view.
         */
        val view: View = mLayoutInflater.inflate(R.layout.activity_main, null, false)

        /**
         * Measure and layout the view. In this example we give an exact
         * width but all the height to be WRAP_CONTENT.
         */
        ViewHelpers.setupView(view)
            .setExactWidthDp(360)
            .setExactHeightDp(640)
            .layout()

        /**
         * Take the actual screenshot. At the end of this call the screenshot
         * is stored on the device, and the gradle plugin takes care of
         * pulling it and displaying it to you in nice ways.
         */
        Screenshot.snap(view)
            .record()
    }

    private fun getPermission(): Array<String> {
        mutableListOf<String>().apply {
            add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }.also {
            return it.toTypedArray()
        }
    }
}