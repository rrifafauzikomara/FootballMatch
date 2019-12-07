package com.rifafauzi.footballmatch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rifafauzi.footballmatch.databinding.ListPlayerBinding
import com.rifafauzi.footballmatch.model.player.Player

/**
 * Created by rrifafauzikomara on 2019-12-07.
 */
 
class PlayersAdapter (private val listener: OnPlayersPressedListener) : ListAdapter<Player, PlayersAdapter.ViewHolder>(
    DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListPlayerBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position), listener, holder.adapterPosition)

    class ViewHolder(private val binding: ListPlayerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Player, listener: OnPlayersPressedListener, position: Int) {
            binding.data = model
            binding.root.setOnClickListener {
                listener.onPlayersPressed(model, position)
            }
            binding.executePendingBindings()
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Player>(){
            override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
                return oldItem.strPlayer == newItem.strPlayer
            }

            override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnPlayersPressedListener {
        fun onPlayersPressed(player: Player, position: Int)
    }

}