package com.rifafauzi.footballmatch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.model.Clubs
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_clubs.view.*

class ClubsAdapter(private val context: Context, private val list: List<Clubs>, private val listener: (Clubs) -> Unit)
    : RecyclerView.Adapter<ClubsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_clubs, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position], listener)
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindItem(items: Clubs, listener: (Clubs) -> Unit) {
            containerView.name.text = items.name
            items.image?.let { Glide.with(containerView).load(it).into(containerView.image) }
            containerView.setOnClickListener {
                listener(items)
            }
        }

    }

}