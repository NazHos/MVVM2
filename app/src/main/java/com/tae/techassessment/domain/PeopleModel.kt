package com.tae.techassessment.domain
data class PeopleModel(
    val name: String,
    val description: String,
    val owner: Owners,
    val size: Int,
    val stargazers_count: Int,
    val watchers_count: Int,
    val has_issues: Boolean,
    val has_projects: Boolean,
    val has_downloads: Boolean,
    val has_wiki: Boolean,
    val has_pages: Boolean,
    val forks_count: Int
    )