package com.rifafauzi.footballmatch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rifafauzi.footballmatch.databinding.ListFavoritesMatchBinding
import com.rifafauzi.footballmatch.db.FavoriteMatch

/**
 * Created by rrifafauzikomara on 2019-11-26.
 */
 
class FavoriteMatchAdapter(private val listener: OnFavoriteMatchPressedListener) : ListAdapter<FavoriteMatch, FavoriteMatchAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListFavoritesMatchBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position), listener, holder.adapterPosition)

    class ViewHolder(private val binding: ListFavoritesMatchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: FavoriteMatch, listener: OnFavoriteMatchPressedListener, position: Int) {
            binding.data = model
            binding.root.setOnClickListener {
                listener.onFavoriteMatchPressed(model, position)
            }

            binding.executePendingBindings()
        }


    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<FavoriteMatch>(){
            override fun areItemsTheSame(oldItem: FavoriteMatch, newItem: FavoriteMatch): Boolean {
                return oldItem.dateEvent == newItem.dateEvent
            }

            override fun areContentsTheSame(oldItem: FavoriteMatch, newItem: FavoriteMatch): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnFavoriteMatchPressedListener {
        fun onFavoriteMatchPressed(favoriteMatch: FavoriteMatch, position: Int)
    }

}