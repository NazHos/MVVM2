package com.tae.techassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tae.techassessment.app.MainViewModel
import com.tae.techassessment.app.MyViewModelFactory
import com.tae.techassessment.app.PeopleAdapter
import com.tae.techassessment.data.MainRepository
import com.tae.techassessment.data.RetrofitService
import com.tae.techassessment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val adapter = PeopleAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)

        binding.recyclerview.adapter = adapter

        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        viewModel.movieList.observe(this, {
            adapter.setPeople(it)
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        viewModel.getAllPeople()

    }
}