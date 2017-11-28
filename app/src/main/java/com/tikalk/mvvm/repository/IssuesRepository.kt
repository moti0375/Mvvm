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
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
 * Created by motibartov on 28/11/2017.
 */
class IssuesRepository @Inject constructor(): Repository {


    companion object {
        val baseUrl = "https://api.github.com/"
    }

    val githubApiService: GitHubApiService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        githubApiService = retrofit.create(GitHubApiService::class.java)
    }

    override fun getIssues(owner: String, repo: String): LiveData<ApiResponse> {

        val liveDate = MutableLiveData<ApiResponse>()
        val observable: Observable<List<Issue>> = githubApiService.getIssues(owner, repo)
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableObserver<List<Issue>>() {
                    override fun onNext(t: List<Issue>) {
                        liveDate.value = ApiResponse(t)
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