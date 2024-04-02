package com.example.testproject.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testproject.model.DetailResponse
import utils.Constants.API_KEY

class TmdbRepo( val service: ApiService) {

    val detailData = MutableLiveData<Response<DetailResponse>>()
//    val castData = MutableLiveData<Response<CastResponse>>()

    val movieDetail: LiveData<Response<DetailResponse>>
        get() = detailData

    suspend fun getMoviesDetails(id: Int) {
        try {
            val result = service.getMovieDetails(id, API_KEY)

            if (result.body() != null) {
                detailData.postValue(Response.Success(result.body()))
            } else {
                detailData.postValue(Response.Error(result.errorBody().toString()))
            }
        } catch (e: Exception){
            detailData.postValue(Response.Error(e.message.toString()))
        }
    }
}

