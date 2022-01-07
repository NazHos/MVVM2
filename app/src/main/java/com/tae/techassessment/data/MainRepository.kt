package com.tae.techassessment.data

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllPeople() = retrofitService.getAllPeople()

}