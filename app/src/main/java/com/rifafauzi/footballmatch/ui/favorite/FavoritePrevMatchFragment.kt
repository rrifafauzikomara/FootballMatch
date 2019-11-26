package com.rifafauzi.footballmatch.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifafauzi.footballmatch.adapter.FavoriteAdapter
import com.rifafauzi.footballmatch.databinding.FragmentFavoritePrevMatchBinding
import com.rifafauzi.footballmatch.db.Favorite
import com.rifafauzi.footballmatch.db.database
import com.rifafauzi.footballmatch.utils.PREV_MATCH
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoritePrevMatchFragment : Fragment() {

    private lateinit var binding: FragmentFavoritePrevMatchBinding
    private lateinit var adapter: FavoriteAdapter
    private var favorites: MutableList<Favorite> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritePrevMatchBinding.inflate(inflater)
        binding.executePendingBindings()

        initRecyclerView()
        showPrevMatch()
        return binding.root
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvPrevMatchFavorite.layoutManager = layoutManager
        adapter = FavoriteAdapter(favorites) {

        }
        binding.rvPrevMatchFavorite.adapter = adapter
    }

    private fun showPrevMatch() {
        favorites.clear()
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(TYPE = {TYPE})",
                    "TYPE" to PREV_MATCH)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }

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
