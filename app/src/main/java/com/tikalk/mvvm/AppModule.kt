package com.tikalk.mvvm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tikalk.mvvm.issues.IssuesViewModel
import com.tikalk.mvvm.issues.IssuesVmFactory
import com.tikalk.mvvm.model.Issue
import com.tikalk.mvvm.model.IssuesJsonDesrializer
import com.tikalk.mvvm.network.GitHubApiService
import com.tikalk.mvvm.repository.IssuesRepository
import com.tikalk.mvvm.repository.Repository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    companion object {
        val BASE_URL = "https://api.github.com/"
    }


    @Provides
    @Singleton
    fun provideGithubApiService(gson : Gson) : GitHubApiService{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build().create(GitHubApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideRepository(issuesRepository: IssuesRepository) : Repository{
        return issuesRepository
    }

    @Provides
    fun provideViewModel(viewModel: IssuesViewModel) : ViewModel{
        return viewModel
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(factory: IssuesVmFactory) : ViewModelProvider.Factory{
        return factory
    }


    @Provides
    @Singleton
    fun provideGson() : Gson{
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeHierarchyAdapter(Issue::class.java, IssuesJsonDesrializer())
        return gsonBuilder.create()

    }


}