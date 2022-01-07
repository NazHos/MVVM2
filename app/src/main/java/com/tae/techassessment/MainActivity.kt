package com.tae.techassessment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tae.techassessment.app.*
import com.tae.techassessment.data.MainRepository
import com.tae.techassessment.data.RetrofitService
import com.tae.techassessment.databinding.ActivityMainBinding
import com.tae.techassessment.domain.PeopleModel

class MainActivity : AppCompatActivity(), MainViewHolder.OnItemClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val adapter = PeopleAdapter(this)
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

    override fun onItemClick(peoples: PeopleModel, position: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("Size", peoples.size)
        intent.putExtra("StargazersCount", peoples.stargazers_count)
        intent.putExtra("WatchersCount", peoples.watchers_count)
        intent.putExtra("HasIssues", peoples.has_issues)
        intent.putExtra("HasProjects", peoples.has_projects)
        intent.putExtra("HasDownloads", peoples.has_downloads)
        intent.putExtra("HasWiki", peoples.has_wiki)
        intent.putExtra("HasPages", peoples.has_pages)
        intent.putExtra("ForksCount", peoples.forks_count)
        startActivity(intent)
    }
}