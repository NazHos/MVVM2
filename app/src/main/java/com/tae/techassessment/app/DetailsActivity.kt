package com.tae.techassessment.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tae.techassessment.databinding.ActivityDetailsBinding
import com.tae.techassessment.databinding.ActivityMainBinding

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val size = intent.getIntExtra("Size", -1)
        val stargazers = intent.getIntExtra("StargazersCount", -1)
        val watchers = intent.getIntExtra("WatchersCount", -1)
        val issues = intent.getBooleanExtra("HasIssues", false)
        val projects = intent.getBooleanExtra("HasProjects", false)
        val downloads = intent.getBooleanExtra("HasDownloads", false)
        val wiki = intent.getBooleanExtra("HasWiki", false)
        val pages = intent.getBooleanExtra("HasPages", false)
        val forks = intent.getIntExtra("ForksCount", -1)
        binding.sizeTV.text = size.toString()
        binding.starGazerTV.text = stargazers.toString()
        binding.watcherTV.text = watchers.toString()
        binding.issuesTV.text = issues.toString()
        binding.projectsTV.text = projects.toString()
        binding.downloadsTV.text = downloads.toString()
        binding.wikiTV.text = wiki.toString()
        binding.pagesTV.text = pages.toString()
        binding.forksTV.text = forks.toString()
    }
}