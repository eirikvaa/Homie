package com.homies.homie.domain

interface PermissionHelper {
    fun isPermissionGranted(vararg permission: String): Boolean
}
