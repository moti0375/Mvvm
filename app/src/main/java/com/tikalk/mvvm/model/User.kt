package com.tikalk.mvvm.model

import android.arch.persistence.room.Entity


@Entity(tableName = "users")
data class User(val id: Int = 0, var name: String = "")