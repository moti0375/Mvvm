package com.tikalk.mvvm

import com.tikalk.mvvm.issues.MainActivity
import com.tikalk.mvvm.repository.RoomModule
import dagger.Component
import javax.inject.Singleton


@Component (modules = arrayOf(AppModule::class, RoomModule::class, ContextModule::class))
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}