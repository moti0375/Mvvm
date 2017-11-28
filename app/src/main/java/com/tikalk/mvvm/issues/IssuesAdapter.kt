package com.tikalk.mvvm.issues

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.tikalk.mvvm.R
import com.tikalk.mvvm.model.Issue
import kotlinx.android.synthetic.main.issue_list_item.view.*

/**
 * Created by motibartov on 28/11/2017.
 */
class IssuesAdapter(private val mContext: Activity) : RecyclerView.Adapter<IssuesAdapter.IssuesViewHolder>() {

    var issues = ArrayList<Issue>()



    override fun onBindViewHolder(holder: IssuesViewHolder, position: Int) {
        holder.bind(issues.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): IssuesViewHolder {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.issue_list_item, parent, false)
        return IssuesViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return issues.size
    }

    fun updateIssues(issues : List<Issue>){
        this.issues.clear()
        this.issues.addAll(issues)
        mContext.runOnUiThread { notifyDataSetChanged() }
    }

    class IssuesViewHolder(var item: View) : RecyclerView.ViewHolder(item){
        fun bind(issue: Issue){
            item.tvTitle.text = issue.title
            item.tvCreator.text = issue.user.login
            item.tvIssueId.text = "${issue.id}"
        }
    }
}