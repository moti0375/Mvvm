package com.tikalk.mvvm.repository

import android.arch.lifecycle.LiveData
import com.tikalk.mvvm.model.ApiResponse

/**
 * Created by motibartov on 28/11/2017.
 */
interface Repository {

    fun getIssues(owner: String, repo: String) : LiveData<ApiResponse>
}