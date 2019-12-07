package com.rifafauzi.footballmatch.ui.detailteam


import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.adapter.ViewPagerFragment
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.databinding.FragmentDetailTeamBinding
import com.rifafauzi.footballmatch.db.FavoriteTeam
import com.rifafauzi.footballmatch.db.database
import com.rifafauzi.footballmatch.model.teams.Team
import com.rifafauzi.footballmatch.ui.player.PlayerListFragment
import com.rifafauzi.footballmatch.ui.teamoverview.TeamOverviewFragment
import com.rifafauzi.footballmatch.utils.ID_TEAM
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class DetailTeamFragment : BaseFragment<FragmentDetailTeamBinding, DetailTeamViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_detail_team
    override fun getViewModelClass() = DetailTeamViewModel::class.java

    private lateinit var idTeam: String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var viewPager : ViewPager
    private lateinit var viewPagerAdapter : ViewPagerFragment
    private lateinit var tabLayout : TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        viewPager = binding.viewPagerDetailTeam
        tabLayout = binding.tabLayoutDetailTeam

        arguments?.let {
            val safeArgs = DetailTeamFragmentArgs.fromBundle(it)
            idTeam = safeArgs.idTeam
        }

        val teamOverView = TeamOverviewFragment()
        val teamPlayer = PlayerListFragment()

        val bundle = Bundle()
        bundle.putString(ID_TEAM, idTeam)
        teamOverView.arguments = bundle
        teamPlayer.arguments = bundle

        val tabOverView = requireContext().resources.getString(R.string.team_overview)
        val tabPlayer = requireContext().resources.getString(R.string.player)

        viewPagerAdapter = ViewPagerFragment(childFragmentManager)
        viewPagerAdapter.addFragment(teamOverView, tabOverView)
        viewPagerAdapter.addFragment(teamPlayer, tabPlayer)

        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager, true)

        favoriteState()

        vm.getTeamDetail(idTeam)
        vm.detailTeam.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hideTeam()
                        showLoading()
                    }
                    is Result.HasData -> {
                        showTeam()
                        hideLoading()
                        displayData(it.data)
                    }
                    is Result.NoData -> {
                        hideTeam()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.empty_data))
                    }
                    is Result.Error -> {
                        hideTeam()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.unknown_error))
                    }
                    is Result.NoInternetConnection -> {
                        hideTeam()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.no_connection))
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_match, menu)
        menuItem = menu
        setFavorite()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun favoriteState(){
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_TEAM)
                .whereArgs("(ID_TEAM = {id})",
                    "id" to idTeam)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite(){
        try {
            context?.database?.use {
                insert(
                    FavoriteTeam.TABLE_TEAM,
                    FavoriteTeam.ID_TEAM to idTeam,
                    FavoriteTeam.TEAM_LOGO to binding.contentDetailTeam.data?.strTeamBadge,
                    FavoriteTeam.TEAM_NAME to binding.contentDetailTeam.data?.strTeam,
                    FavoriteTeam.TEAM_DESCRIPTION to binding.contentDetailTeam.data?.strDescriptionEN,
                    FavoriteTeam.LEAGUE_NAME to binding.contentDetailTeam.data?.strLeague,
                    FavoriteTeam.STADIUM_NAME to binding.contentDetailTeam.data?.strStadium,
                    FavoriteTeam.STADIUM_LOC to binding.contentDetailTeam.data?.strStadiumLocation,
                    FavoriteTeam.STADIUM_DESC to binding.contentDetailTeam.data?.strStadiumDescription,
                    FavoriteTeam.STADIUM_CAPACITY to binding.contentDetailTeam.data?.intStadiumCapacity,
                    FavoriteTeam.COUNTRY to binding.contentDetailTeam.data?.strCountry,
                    FavoriteTeam.STADIUM_BANNER to binding.contentDetailTeam.data?.strStadiumThumb,
                    FavoriteTeam.TEAM_JERSEY to binding.contentDetailTeam.data?.strTeamJersey,
                    FavoriteTeam.TEAM_BANNER to binding.contentDetailTeam.data?.strTeamBanner,
                    FavoriteTeam.SPORT to binding.contentDetailTeam.data?.strSport
                )
            }
            snackBar(resources.getString(R.string.add_to_favorite))
        } catch (e: SQLiteConstraintException){
            Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            context?.database?.use {
                delete(
                    FavoriteTeam.TABLE_TEAM,
                    "(ID_TEAM = {id})",
                    "id" to idTeam
                )
            }
            snackBar(resources.getString(R.string.delete_from_favorite))
            findNavController().navigateUp()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_add_to_favorites)
    }

    private fun displayData(data: List<Team>) {
        binding.contentDetailTeam.data = data[0]
    }

    private fun showLoading() {
        binding.showLoading = true
    }

    private fun hideLoading() {
        binding.showLoading = false
    }

    private fun showTeam() {
        binding.showData = true
    }

    private fun hideTeam() {
        binding.showData = false
    }

}
