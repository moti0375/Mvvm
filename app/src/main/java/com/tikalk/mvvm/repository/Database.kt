package com.tikalk.mvvm.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.tikalk.mvvm.model.Issue


@Database(entities = arrayOf(Issue::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun getIssueDao() : IssuesDao
}