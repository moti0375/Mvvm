package com.tikalk.mvvm.repository

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.tikalk.mvvm.model.Issue


@Dao
interface IssuesDao {

    @Insert(onConflict = REPLACE)
    fun insertIssues(issues : List<Issue>?)

    @Insert
    fun insertIssue(issue : Issue)

    @Query("SELECT * FROM issues")
    fun getAllIssues() : LiveData<List<Issue>>

    @Query("SELECT * FROM issues WHERE id = :arg0")
    fun getIssueById(id: Int): Issue

    @Update
    fun updateIssue(issue: Issue)

    @Delete
    fun deleteIssue(issue: Issue)
}