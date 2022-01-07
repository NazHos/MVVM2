package com.tae.techassessment.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tae.techassessment.MainActivity
import com.tae.techassessment.databinding.AdapterPeopleBinding
import com.tae.techassessment.domain.PeopleModel

class PeopleAdapter(var clickListener: MainViewHolder.OnItemClickListener) : RecyclerView.Adapter<MainViewHolder>() {

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
        holder.initialize(peopleList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }
}

class MainViewHolder(val binding: AdapterPeopleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun initialize(people: PeopleModel, action: OnItemClickListener) {
        binding.name.text = people.name
        Glide.with(itemView.context).load(people.owner.avatar_url).into(binding.imageview)
        itemView.setOnClickListener{
            action.onItemClick(people, adapterPosition)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(peoples : PeopleModel, position: Int)
    }

}