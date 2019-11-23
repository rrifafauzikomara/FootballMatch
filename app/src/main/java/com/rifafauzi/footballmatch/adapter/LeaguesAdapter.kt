package com.rifafauzi.footballmatch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rifafauzi.footballmatch.databinding.ListLeaguesBinding
import com.rifafauzi.footballmatch.model.leagues.Leagues

/**
 * Created by rrifafauzikomara on 2019-11-22.
 */
 
class LeaguesAdapter (private val listener: OnLeaguesPressedListener) : ListAdapter<Leagues, LeaguesAdapter.ViewHolder>(
    DiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListLeaguesBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position), listener, holder.adapterPosition)

    class ViewHolder(private val binding: ListLeaguesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Leagues, listener: OnLeaguesPressedListener, position: Int) {
            binding.data = model
            binding.root.setOnClickListener {
                listener.onLeaguesPressed(model, position)
            }

            binding.executePendingBindings()
        }


    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Leagues>(){
            override fun areItemsTheSame(oldItem: Leagues, newItem: Leagues): Boolean {
                return oldItem.strLeague == newItem.strLeague
            }

            override fun areContentsTheSame(oldItem: Leagues, newItem: Leagues): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnLeaguesPressedListener {
        fun onLeaguesPressed(leagues: Leagues, position: Int)
    }

}