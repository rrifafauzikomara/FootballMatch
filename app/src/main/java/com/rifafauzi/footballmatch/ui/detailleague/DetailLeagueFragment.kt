package com.rifafauzi.footballmatch.ui.detailleague

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.adapter.ViewPagerFragment
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.databinding.FragmentDetailLeagueBinding
import com.rifafauzi.footballmatch.model.leagues.Leagues
import com.rifafauzi.footballmatch.ui.nextmatch.NextMatchFragment
import com.rifafauzi.footballmatch.ui.previousmatch.PreviousMatchFragment
import com.rifafauzi.footballmatch.ui.standings.StandingsFragment
import com.rifafauzi.footballmatch.ui.teams.TeamFragment
import com.rifafauzi.footballmatch.utils.ID_LEAGUES

/**
 * A simple [Fragment] subclass.
 */
class DetailLeagueFragment : BaseFragment<FragmentDetailLeagueBinding, DetailLeagueViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_detail_league
    override fun getViewModelClass() = DetailLeagueViewModel::class.java

    private var idLeague: String? = null

    private lateinit var viewPager : ViewPager
    private lateinit var viewPagerAdapter : ViewPagerFragment
    private lateinit var tabLayout : TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = binding.viewPagerDetailLeagues
        tabLayout = binding.tabLayoutDetailLeagues

        arguments?.let {
            val safeArgs = DetailLeagueFragmentArgs.fromBundle(it)
            idLeague = safeArgs.idLeague
        }

        val previousMatchFragment = PreviousMatchFragment()
        val nextMatchFragment = NextMatchFragment()
        val teamFragment = TeamFragment()
        val standingsFragment = StandingsFragment()

        val bundle = Bundle()
        bundle.putString(ID_LEAGUES, idLeague)
        nextMatchFragment.arguments = bundle
        previousMatchFragment.arguments = bundle
        teamFragment.arguments = bundle
        standingsFragment.arguments = bundle

        val tabPrevMatch = requireContext().resources.getString(R.string.previous_match)
        val tabNextMatch = requireContext().resources.getString(R.string.next_match)
        val tabTeam = requireContext().resources.getString(R.string.team)
        val tabStandings = requireContext().resources.getString(R.string.standings)

        viewPagerAdapter = ViewPagerFragment(childFragmentManager)
        viewPagerAdapter.addFragment(previousMatchFragment, tabPrevMatch)
        viewPagerAdapter.addFragment(nextMatchFragment, tabNextMatch)
        viewPagerAdapter.addFragment(teamFragment, tabTeam)
        viewPagerAdapter.addFragment(standingsFragment, tabStandings)

        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager,true)

        vm.getDetailLeague(idLeague)
        vm.detailLeague.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hideLeagues()
                        showLoading()
                    }
                    is Result.HasData -> {
                        showLeagues()
                        hideLoading()
                        displayData(it.data)
                    }
                    is Result.NoData -> {
                        hideLeagues()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.empty_data))
                    }
                    is Result.Error -> {
                        hideLeagues()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.unknown_error))
                    }
                    is Result.NoInternetConnection -> {
                        hideLeagues()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.no_connection))
                    }
                }
            }
        })

    }

    private fun displayData(data: List<Leagues>) {
        binding.contentDetailLeagues.data = data[0]
    }

    private fun showLoading() {
        binding.showLoading = true
    }

    private fun hideLoading() {
        binding.showLoading = false
    }

    private fun showLeagues() {
        binding.showData = true
    }

    private fun hideLeagues() {
        binding.showData = false
    }

}