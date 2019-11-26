package com.rifafauzi.footballmatch.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifafauzi.footballmatch.adapter.FavoriteAdapter
import com.rifafauzi.footballmatch.databinding.FragmentFavoriteNextMatchBinding
import com.rifafauzi.footballmatch.db.Favorite
import com.rifafauzi.footballmatch.db.database
import com.rifafauzi.footballmatch.utils.NEXT_MATCH
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoriteNextMatchFragment : Fragment(), FavoriteAdapter.OnFavoriteMatchPressedListener {

    override fun onFavoriteMatchPressed(favorite: Favorite, position: Int) {
        launchMatchDetail(favorite.idEvent, favorite.type)
    }

    private lateinit var binding: FragmentFavoriteNextMatchBinding
    private val adapter = FavoriteAdapter(this)
    private var favorites: MutableList<Favorite> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteNextMatchBinding.inflate(inflater)
        binding.executePendingBindings()

        initRecyclerView()
        showNextMatch()
        return binding.root
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvNextMatchFavorite.layoutManager = layoutManager
        binding.rvNextMatchFavorite.adapter = adapter
    }

    private fun showNextMatch() {
        favorites.clear()
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(TYPE = {TYPE})",
                    "TYPE" to NEXT_MATCH)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.submitList(favorites)
        }

        if (favorites.isEmpty()) {
            hideData()
            showLayoutEmpty()
        } else {
            showData()
            hideLayoutEmpty()
        }
    }

    private fun launchMatchDetail(idEvent: String, type: String) {
        val action =
            FavoriteNextMatchFragmentDirections.actionFavoriteNextMatchFragmentToDetailMatchFragment(idEvent, type)
        findNavController().navigate(action)
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
