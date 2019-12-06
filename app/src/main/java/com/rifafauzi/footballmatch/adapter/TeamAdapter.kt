package com.rifafauzi.footballmatch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rifafauzi.footballmatch.databinding.ListTeamsBinding
import com.rifafauzi.footballmatch.model.teams.Team

/**
 * Created by rrifafauzikomara on 2019-12-06.
 */
 
class TeamAdapter (private val listener: OnTeamsPressedListener) : ListAdapter<Team, TeamAdapter.ViewHolder>(
    DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListTeamsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position), listener, holder.adapterPosition)

    class ViewHolder(private val binding: ListTeamsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Team, listener: OnTeamsPressedListener, position: Int) {
            binding.data = model
            binding.root.setOnClickListener {
                listener.onTeamsPressed(model, position)
            }
            binding.executePendingBindings()
        }

    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Team>(){
            override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
                return oldItem.strLeague == newItem.strLeague
            }

            override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnTeamsPressedListener {
        fun onTeamsPressed(team: Team, position: Int)
    }

}