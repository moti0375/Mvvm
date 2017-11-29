package com.tikalk.mvvm.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.tikalk.mvvm.model.ApiResponse
import com.tikalk.mvvm.model.Issue
import com.tikalk.mvvm.network.GitHubApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class IssuesRepository @Inject constructor(gitHubApiService: GitHubApiService, database: Database): Repository {

//    val roomDatabase : Database
    companion object {
        val baseUrl = "https://api.github.com/"

    }

    val githubApiService: GitHubApiService
    val issuesDatabase : Database

    init {
        githubApiService = gitHubApiService
        issuesDatabase = database
    }

    override fun getIssues(owner: String, repo: String): LiveData<ApiResponse> {

        val liveDate = MutableLiveData<ApiResponse>()
        val observable: Observable<List<Issue>> = githubApiService.getIssues(owner, repo)
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableObserver<List<Issue>>() {
                    override fun onNext(t: List<Issue>) {
                        liveDate.value = ApiResponse(t)
                        val issues = liveDate.value?.issues

                        issuesDatabase.getIssueDao().insertIssues(issues)
                    }

                    override fun onError(e: Throwable) {
                        liveDate.value = ApiResponse(e)
                    }

                    override fun onComplete() {
                    }

                })

        return liveDate
    }
}