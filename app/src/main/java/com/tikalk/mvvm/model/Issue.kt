package com.tikalk.mvvm.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose


@Entity(tableName = "issues")
data class Issue(@PrimaryKey(autoGenerate = true) @Expose(serialize = false, deserialize = false)
                 var id: Int = 0, var githubId: Int = 0, var title: String = "", var number: Int = 0, var userName: String? = "")