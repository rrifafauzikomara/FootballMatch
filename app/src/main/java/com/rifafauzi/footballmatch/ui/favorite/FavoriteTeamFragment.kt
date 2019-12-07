package com.rifafauzi.footballmatch.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifafauzi.footballmatch.adapter.FavoriteTeamAdapter
import com.rifafauzi.footballmatch.databinding.FragmentFavoriteTeamBinding
import com.rifafauzi.footballmatch.db.FavoriteTeam
import com.rifafauzi.footballmatch.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamFragment : Fragment(), FavoriteTeamAdapter.OnFavoriteTeamPressedListener {

    override fun onFavoriteTeamPressed(favoriteTeam: FavoriteTeam, position: Int) {
        val action = FavoriteMatchFragmentDirections.actionLaunchDetailTeamFragment(favoriteTeam.idTeam)
        findNavController().navigate(action)
    }

    private lateinit var binding: FragmentFavoriteTeamBinding
    private val adapter = FavoriteTeamAdapter(this)
    private var favoriteTeam: MutableList<FavoriteTeam> = mutableListOf()

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
        binding.rvTeamsFavorite.adapter = adapter
    }

    private fun showTeam() {
        favoriteTeam.clear()
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favoriteTeam.addAll(favorite)
            adapter.submitList(favoriteTeam)
        }

        if (favoriteTeam.isEmpty()) {
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
