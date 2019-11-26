package com.rifafauzi.footballmatch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rifafauzi.footballmatch.databinding.ListFavoritesBinding
import com.rifafauzi.footballmatch.db.Favorite

/**
 * Created by rrifafauzikomara on 2019-11-26.
 */
 
class FavoriteAdapter(private val listener: OnFavoriteMatchPressedListener) : ListAdapter<Favorite, FavoriteAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListFavoritesBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position), listener, holder.adapterPosition)

    class ViewHolder(private val binding: ListFavoritesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Favorite, listener: OnFavoriteMatchPressedListener, position: Int) {
            binding.data = model
            binding.root.setOnClickListener {
                listener.onFavoriteMatchPressed(model, position)
            }

            binding.executePendingBindings()
        }


    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Favorite>(){
            override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem.dateEvent == newItem.dateEvent
            }

            override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnFavoriteMatchPressedListener {
        fun onFavoriteMatchPressed(favorite: Favorite, position: Int)
    }

}