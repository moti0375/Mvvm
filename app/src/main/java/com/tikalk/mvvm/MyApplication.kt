package com.tikalk.mvvm

import android.app.Application

class MyApplication : Application() {

    lateinit var component : AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
    }

    fun getAppComponent() : AppComponent{
        return component
    }
}