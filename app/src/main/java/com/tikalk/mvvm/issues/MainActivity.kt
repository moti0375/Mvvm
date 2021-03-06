package com.tikalk.mvvm.issues

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.tikalk.mvvm.MyApplication
import com.tikalk.mvvm.R
import com.tikalk.mvvm.model.ApiResponse
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    lateinit var issuesAdapter : IssuesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        val TAG = "TAG_MainActivity"
    }

    lateinit var issuesViewModel: IssuesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApplication).getAppComponent().inject(this)

        setViews()
        issuesViewModel = ViewModelProviders.of(this, viewModelFactory).get(IssuesViewModel::class.java)
        issuesViewModel.apiResponse.observe(this, object : Observer<ApiResponse>{
            override fun onChanged(t: ApiResponse?) {
                Log.i(TAG, "onChanged + ${t?.issues}")
                if(t != null){
                    issuesAdapter.updateIssues(t.issues)
                }
            }
        })
        issuesViewModel.loadIssues("square", "retrofit")
    }

    private fun setViews() {
        rvIssuesList.layoutManager = LinearLayoutManager(this)
        issuesAdapter = IssuesAdapter(this)
        rvIssuesList.adapter = issuesAdapter
    }
}
