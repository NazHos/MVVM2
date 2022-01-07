package com.tae.techassessment.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tae.techassessment.data.MainRepository
import com.tae.techassessment.domain.PeopleModel
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<List<PeopleModel>>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllPeople() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAllPeople()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    movieList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}