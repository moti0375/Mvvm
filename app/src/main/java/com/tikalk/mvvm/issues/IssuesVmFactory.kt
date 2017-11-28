package com.tikalk.mvvm.issues

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



/**
 * Created by motibartov on 28/11/2017.
 */
class IssuesVmFactory @Inject constructor(viewModel: IssuesViewModel): ViewModelProvider.Factory {

     val issuesViewModel : IssuesViewModel = viewModel

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(IssuesViewModel::class.java)){
            return issuesViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}