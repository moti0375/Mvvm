package com.tikalk.mvvm

import android.app.Application

/**
 * Created by motibartov on 28/11/2017.
 */
class MyApplication : Application() {

    lateinit var component : AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().build()
    }

    fun getAppComponent() : AppComponent{
        return component
    }
}