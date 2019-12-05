package com.rifafauzi.footballmatch.ui.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.databinding.FragmentFavoriteNextMatchBinding
import com.rifafauzi.footballmatch.databinding.FragmentFavoriteTeamBinding
import com.rifafauzi.footballmatch.db.Favorite

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteTeamBinding

    private var favorites: MutableList<Favorite> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteTeamBinding.inflate(inflater)
        binding.executePendingBindings()

        initRecyclerView()
        showTeam()
        return binding.root

    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvTeamsFavorite.layoutManager = layoutManager
//        binding.rvTeamsFavorite.adapter = adapter
    }

    private fun showTeam() {
        favorites.clear()
//        context?.database?.use {
//            val result = select(Favorite.TABLE_FAVORITE)
//                .whereArgs("(TYPE = {TYPE})",
//                    "TYPE" to NEXT_MATCH)
//            val favorite = result.parseList(classParser<Favorite>())
//            favorites.addAll(favorite)
//            adapter.submitList(favorites)
//        }

        if (favorites.isEmpty()) {
            hideData()
            showLayoutEmpty()
        } else {
            showData()
            hideLayoutEmpty()
        }
    }

    private fun showData() {
        binding.showData = true
    }

    private fun hideData() {
        binding.showData = false
    }

    private fun showLayoutEmpty() {
        binding.showEmpty = true
    }

    private fun hideLayoutEmpty() {
        binding.showEmpty = false
    }

}
