package com.rifafauzi.footballmatch.ui.leagues

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifafauzi.footballmatch.data.local.entity.Leagues
import com.rifafauzi.footballmatch.databinding.ListLeaguesBinding

/**
 * Created by rrifafauzikomara on 2019-10-29.
 */
 
class LeaguesAdapter(private var leagues: List<Leagues>, private var listener: OnLeaguesPressedListener)
    : RecyclerView.Adapter<LeaguesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListLeaguesBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return leagues.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(leagues[position], listener, holder.adapterPosition)
    }

    interface OnLeaguesPressedListener {
        fun onLeaguesPressed(banner: Leagues, position: Int)
    }

    fun refreshData(leagues: List<Leagues>) {
        this.leagues = leagues
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ListLeaguesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Leagues, listener: OnLeaguesPressedListener, position: Int) {
            binding.data = model
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                listener.onLeaguesPressed(model, position)
            }
        }
    }
}