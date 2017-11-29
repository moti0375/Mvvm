package com.tikalk.mvvm.repository

import android.content.Context
import dagger.Module
import dagger.Provides
import android.arch.persistence.room.Room




@Module
class RoomModule{

    @Provides
    fun provideDatabase(context: Context) : Database{
        return Room.databaseBuilder(context,
                Database::class.java, "issues-db").allowMainThreadQueries().build()
    }

}
