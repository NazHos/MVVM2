package com.tae.techassessment.data

import com.tae.techassessment.domain.PeopleModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("repos")
    suspend fun getAllPeople() : Response<List<PeopleModel>>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/orgs/square/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}