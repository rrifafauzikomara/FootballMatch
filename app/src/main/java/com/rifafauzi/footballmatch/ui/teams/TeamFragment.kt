package com.rifafauzi.footballmatch.ui.teams


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.adapter.TeamAdapter
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.databinding.FragmentTeamBinding
import com.rifafauzi.footballmatch.model.teams.Team
import com.rifafauzi.footballmatch.ui.detailleague.DetailLeagueFragmentDirections
import com.rifafauzi.footballmatch.utils.ID_LEAGUES

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : BaseFragment<FragmentTeamBinding, TeamViewModel>(), TeamAdapter.OnTeamsPressedListener {

    override fun getLayoutResourceId() = R.layout.fragment_team
    override fun getViewModelClass() = TeamViewModel::class.java

    private val adapter = TeamAdapter(this)
    private var idLeague:String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        idLeague = arguments?.getString(ID_LEAGUES)

        vm.getListTeams(idLeague)
        vm.team.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hideTeams()
                        hideLayoutEmpty()
                        showLoading()
                    }
                    is Result.HasData -> {
                        showTeams()
                        hideLayoutEmpty()
                        hideLoading()
                        refreshData(it.data)
                    }
                    is Result.NoData -> {
                        hideTeams()
                        hideLoading()
                        showLayoutEmpty()
                    }
                    is Result.Error -> {
                        hideTeams()
                        hideLoading()
                        hideLayoutEmpty()
                        longSnackBar(resources.getString(R.string.unknown_error))
                    }
                    is Result.NoInternetConnection -> {
                        hideTeams()
                        hideLoading()
                        hideLayoutEmpty()
                        longSnackBar(resources.getString(R.string.no_connection))
                    }
                }
            }
        })

        launchSearchTeam()

    }

    override fun onTeamsPressed(team: Team, position: Int) {
        launchDetailTeam(team.idTeam)
    }

    private fun launchSearchTeam() {
        binding.layoutSearch.setOnClickListener {
            val action = DetailLeagueFragmentDirections.actionLaunchSearchTeamFragment()
            findNavController().navigate(action)
        }
    }

    private fun launchDetailTeam(idTeam: String) {
        val action = DetailLeagueFragmentDirections.actionDetailLeagueFragmentToDetailTeamFragment(idTeam)
        findNavController().navigate(action)
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvTeams.layoutManager = layoutManager
        binding.rvTeams.adapter = adapter
    }

    private fun refreshData(data: List<Team>) {
        adapter.submitList(data)
    }

    private fun showLoading() {
        binding.showLoading = true
    }

    private fun hideLoading() {
        binding.showLoading = false
    }

    private fun showTeams() {
        binding.showData = true
    }

    private fun hideTeams() {
        binding.showData = false
    }

    private fun showLayoutEmpty() {
        binding.layoutEmptyData.visibility = View.VISIBLE
    }

    private fun hideLayoutEmpty() {
        binding.layoutEmptyData.visibility = View.GONE
    }

}
