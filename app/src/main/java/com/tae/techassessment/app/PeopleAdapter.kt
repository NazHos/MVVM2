package com.tae.techassessment.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tae.techassessment.databinding.AdapterPeopleBinding
import com.tae.techassessment.domain.PeopleModel

class PeopleAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var peopleList = mutableListOf<PeopleModel>()

    fun setPeople(peoples: List<PeopleModel>) {
        this.peopleList = peoples.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterPeopleBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val people = peopleList[position]
        holder.binding.name.text = people.name
        Glide.with(holder.itemView.context).load(people.owner.avatar_url).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }
}

class MainViewHolder(val binding: AdapterPeopleBinding) : RecyclerView.ViewHolder(binding.root) {

}