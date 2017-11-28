package com.tikalk.mvvm.model

/**
 * Created by motibartov on 28/11/2017.
 */

class ApiResponse {
    var issues: List<Issue>
    var error: Throwable? = null

    constructor(issues: List<Issue>) {
        this.issues = issues
        this.error = null
    }

    constructor(error: Throwable) {
        this.error = error
        this.issues = emptyList()
    }

    // Getters...
}


