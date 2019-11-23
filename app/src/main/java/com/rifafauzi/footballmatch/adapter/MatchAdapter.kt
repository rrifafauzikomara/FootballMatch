package com.rifafauzi.footballmatch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rifafauzi.footballmatch.databinding.ListMatchBinding
import com.rifafauzi.footballmatch.model.match.Match

/**
 * Created by rrifafauzikomara on 2019-11-23.
 */
 
class MatchAdapter (private val listener: OnMatchPressedListener) : ListAdapter<Match, MatchAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListMatchBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position), listener, holder.adapterPosition)

    class ViewHolder(private val binding: ListMatchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Match, listener: OnMatchPressedListener, position: Int) {
            binding.data = model
            binding.root.setOnClickListener {
                listener.onMatchPressed(model, position)
            }

            binding.executePendingBindings()
        }


    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Match>(){
            override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean {
                return oldItem.dateEvent == newItem.dateEvent
            }

            override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnMatchPressedListener {
        fun onMatchPressed(match: Match, position: Int)
    }
}