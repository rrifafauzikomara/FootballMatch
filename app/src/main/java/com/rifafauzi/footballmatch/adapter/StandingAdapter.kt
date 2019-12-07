package com.rifafauzi.footballmatch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rifafauzi.footballmatch.databinding.ListStandingBinding
import com.rifafauzi.footballmatch.model.standing.Standings

/**
 * Created by rrifafauzikomara on 2019-12-07.
 */

class StandingAdapter : ListAdapter<Standings, StandingAdapter.ViewHolder>(
    DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListStandingBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(private val binding: ListStandingBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Standings) {
            binding.data = model
            binding.executePendingBindings()
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Standings>(){
            override fun areItemsTheSame(oldItem: Standings, newItem: Standings): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Standings, newItem: Standings): Boolean {
                return oldItem == newItem
            }

        }
    }

}