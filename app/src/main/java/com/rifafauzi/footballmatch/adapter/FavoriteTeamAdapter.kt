package com.rifafauzi.footballmatch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rifafauzi.footballmatch.databinding.ListFavoritesTeamBinding
import com.rifafauzi.footballmatch.db.FavoriteTeam

/**
 * Created by rrifafauzikomara on 2019-12-07.
 */
 
class FavoriteTeamAdapter (private var listener: OnFavoriteTeamPressedListener) : ListAdapter<FavoriteTeam, FavoriteTeamAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListFavoritesTeamBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position), listener, holder.adapterPosition)

    class ViewHolder(private val binding: ListFavoritesTeamBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: FavoriteTeam, listener: OnFavoriteTeamPressedListener, position: Int) {
            binding.data = model
            binding.root.setOnClickListener {
                listener.onFavoriteTeamPressed(model, position)
            }

            binding.executePendingBindings()
        }


    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<FavoriteTeam>(){
            override fun areItemsTheSame(oldItem: FavoriteTeam, newItem: FavoriteTeam): Boolean {
                return oldItem.strTeam == newItem.strTeam
            }

            override fun areContentsTheSame(oldItem: FavoriteTeam, newItem: FavoriteTeam): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnFavoriteTeamPressedListener {
        fun onFavoriteTeamPressed(favoriteTeam: FavoriteTeam, position: Int)
    }

}