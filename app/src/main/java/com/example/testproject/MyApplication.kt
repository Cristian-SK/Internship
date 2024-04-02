package com.example.testproject

import android.app.Application
import com.example.testproject.api.ApiService
import com.example.testproject.api.RetrofitHelper
import com.example.testproject.api.TmdbRepo

class MyApplication : Application(){

    lateinit var tmdbRepo : TmdbRepo
    override fun onCreate() {
        super.onCreate()

        init()
    }

    private fun init() {
        val service = RetrofitHelper.getInstance().create(ApiService::class.java)
       tmdbRepo = TmdbRepo(service)
    }


}