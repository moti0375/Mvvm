package com.tikalk.mvvm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.tikalk.mvvm.issues.IssuesViewModel
import com.tikalk.mvvm.issues.IssuesVmFactory
import com.tikalk.mvvm.network.GitHubApiService
import com.tikalk.mvvm.repository.IssuesRepository
import com.tikalk.mvvm.repository.Repository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by motibartov on 28/11/2017.
 */
@Module
class AppModule {

    companion object {
        val BASE_URL = "https://api.github.com/"
    }


    @Provides
    @Singleton
    fun provideGithubApiService() : GitHubApiService{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build().create(GitHubApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideRepsitory(issuesRepository: IssuesRepository) : Repository{
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


}