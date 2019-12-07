package com.rifafauzi.footballmatch.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifafauzi.footballmatch.adapter.FavoriteMatchAdapter
import com.rifafauzi.footballmatch.databinding.FragmentFavoritePrevMatchBinding
import com.rifafauzi.footballmatch.db.FavoriteMatch
import com.rifafauzi.footballmatch.db.database
import com.rifafauzi.footballmatch.utils.PREV_MATCH
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoritePrevMatchFragment : Fragment(), FavoriteMatchAdapter.OnFavoriteMatchPressedListener {

    override fun onFavoriteMatchPressed(favoriteMatch: FavoriteMatch, position: Int) {
        val action = FavoriteMatchFragmentDirections.actionFavoriteFragmentToDetailMatchFragment(favoriteMatch.idEvent, favoriteMatch.type)
        findNavController().navigate(action)
    }

    private lateinit var binding: FragmentFavoritePrevMatchBinding
    private val adapter = FavoriteMatchAdapter(this)
    private var favoriteMatches: MutableList<FavoriteMatch> = mutableListOf()

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
        binding.rvPrevMatchFavorite.adapter = adapter
    }

    private fun showPrevMatch() {
        favoriteMatches.clear()
        context?.database?.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE)
                .whereArgs("(TYPE = {TYPE})",
                    "TYPE" to PREV_MATCH)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            favoriteMatches.addAll(favorite)
            adapter.submitList(favoriteMatches)
        }

        if (favoriteMatches.isEmpty()) {
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
