package com.tikalk.mvvm

import com.tikalk.mvvm.issues.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by motibartov on 28/11/2017.
 */
@Component (modules = arrayOf(AppModule::class))
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}