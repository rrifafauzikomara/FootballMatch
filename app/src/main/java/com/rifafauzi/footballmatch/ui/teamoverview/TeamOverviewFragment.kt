package com.rifafauzi.footballmatch.ui.teamoverview


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.databinding.FragmentTeamOverviewBinding
import com.rifafauzi.footballmatch.model.teams.Team
import com.rifafauzi.footballmatch.utils.ID_TEAM

/**
 * A simple [Fragment] subclass.
 */
class TeamOverviewFragment : BaseFragment<FragmentTeamOverviewBinding, TeamOverviewViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_team_overview
    override fun getViewModelClass() = TeamOverviewViewModel::class.java

    private var idTeam:String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        idTeam = arguments?.getString(ID_TEAM)

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
        binding.data = data[0]
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
