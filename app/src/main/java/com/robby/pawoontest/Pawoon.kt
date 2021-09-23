package com.robby.pawoontest

import androidx.multidex.MultiDexApplication

class Pawoon : MultiDexApplication() {

    companion object {
        lateinit var instance : Pawoon

        fun getApp() : Pawoon {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}