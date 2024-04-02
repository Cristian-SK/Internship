package com.example.testproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testproject.api.Response
import com.example.testproject.api.TmdbRepo
import com.example.testproject.model.DetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewmodel(val repo: TmdbRepo, id:Int) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getMoviesDetails(id)
        }
    }
    val movieDetails: LiveData<Response<DetailResponse>>
        get()= repo.movieDetail
}