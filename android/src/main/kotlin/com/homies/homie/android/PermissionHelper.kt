package com.homies.homie.android

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.homies.homie.domain.PermissionHelper

class PermissionHelperImpl(private val context: Context) : PermissionHelper {
    override fun isPermissionGranted(vararg permission: String): Boolean =
        permission.all { ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED }
}
