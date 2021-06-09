package com.htk.assginment2

import android.app.Application
import com.htk.assginment2.data.SoptUserAuthStorage.initSharedPreference

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        initSharedPreference(this)
    }
}