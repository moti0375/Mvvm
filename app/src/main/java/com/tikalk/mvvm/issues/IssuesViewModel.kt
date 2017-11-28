package com.tikalk.mvvm.issues

import android.arch.lifecycle.*
import com.tikalk.mvvm.model.ApiResponse
import com.tikalk.mvvm.model.User
import com.tikalk.mvvm.repository.Repository
import com.tikalk.mvvm.repository.IssuesRepository
import javax.inject.Inject

/**
 * Created by motibartov on 28/11/2017.
 */
class IssuesViewModel : ViewModel {

    val apiResponse : MediatorLiveData<ApiResponse>
    val repository : Repository

    @Inject
    constructor(repo: Repository){  //No arguments constructor
        apiResponse = MediatorLiveData()
        repository = repo
    }


    fun getApiResponse() : LiveData<ApiResponse>{
        return apiResponse
    }

    fun loadIssues(user: String, repo: String) : LiveData<ApiResponse>{
        apiResponse.addSource(repository.getIssues(user, repo)) { t -> apiResponse.value = t }
        return apiResponse
    }


}