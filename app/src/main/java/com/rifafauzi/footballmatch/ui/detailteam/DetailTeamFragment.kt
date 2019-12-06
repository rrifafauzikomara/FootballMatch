package com.rifafauzi.footballmatch.ui.detailteam


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
import com.rifafauzi.footballmatch.databinding.FragmentDetailTeamBinding
import com.rifafauzi.footballmatch.model.teams.Team
import com.rifafauzi.footballmatch.ui.player.PlayerListFragment
import com.rifafauzi.footballmatch.ui.teamoverview.TeamOverviewFragment
import com.rifafauzi.footballmatch.utils.ID_TEAM

/**
 * A simple [Fragment] subclass.
 */
class DetailTeamFragment : BaseFragment<FragmentDetailTeamBinding, DetailTeamViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_detail_team
    override fun getViewModelClass() = DetailTeamViewModel::class.java

    private var idTeam: String? = null

    private lateinit var viewPager : ViewPager
    private lateinit var viewPagerAdapter : ViewPagerFragment
    private lateinit var tabLayout : TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
