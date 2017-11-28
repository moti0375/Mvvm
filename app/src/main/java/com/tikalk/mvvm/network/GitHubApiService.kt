package com.tikalk.mvvm.network

import com.tikalk.mvvm.model.Issue
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by motibartov on 28/11/2017.
 */
interface GitHubApiService {

    @GET("/repos/{owner}/{repo}/issues")
    fun getIssues(@Path("owner") owner: String, @Path("repo") repo: String) : Observable<List<Issue>>
}