package com.hb.sharedpreference

import androidx.multidex.MultiDexApplication

class MainApplication : MultiDexApplication() {

    private var sApplication: MainApplication? = null
    override fun onCreate() {
        super.onCreate()
        sApplication = this

    }

    fun getApplication(): MainApplication? {
        return sApplication
    }




}
