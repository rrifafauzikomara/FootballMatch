package com.rifafauzi.footballmatch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifafauzi.footballmatch.databinding.ListFavoritesBinding
import com.rifafauzi.footballmatch.db.Favorite

/**
 * Created by rrifafauzikomara on 2019-11-26.
 */
 
class FavoriteAdapter(
    private var items: List<Favorite>,
    private val clickListener: (Favorite) -> Unit) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListFavoritesBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], clickListener)

    class ViewHolder(private val binding: ListFavoritesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Favorite, clickListener: (league: Favorite) -> Unit) {
            binding.data = model
            binding.root.setOnClickListener {
                clickListener(model)
            }

            binding.executePendingBindings()
        }


    }
}