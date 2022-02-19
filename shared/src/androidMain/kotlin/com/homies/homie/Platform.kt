package com.homies.homie

import android.content.Context

/**
 * The actual [Platform] class for Android platform-specific dependencies.
 * Holds a reference to the app [Context], that can be used from the Kotlin/JVM code.
 */
@Suppress("StaticFieldLeak")
actual object Platform {
    lateinit var context: Context
}
