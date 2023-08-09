package ru.habr.version

import android.app.Application
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        version = getAppVersion()
    }

    fun getAppVersion(): String {
        var pInfo: PackageInfo? = null
        try {
            val pm = packageManager
            if (pm != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    pInfo = pm.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
                } else {
                    pInfo = pm.getPackageInfo(packageName, 0)
                }
            }
        } catch (ignored: Exception) {
        }
        if (pInfo == null) {
            pInfo = PackageInfo()
            pInfo.versionName = "0.0.0"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                pInfo.longVersionCode = 0
            } else {
                pInfo.versionCode = 0
            }
        }
        var version = pInfo.versionName + "."
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            version += pInfo.longVersionCode
        } else {
            version += pInfo.versionCode.toLong()
        }
        return version
    }

    companion object {
        var version: String = ""
    }
}