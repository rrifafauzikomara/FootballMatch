package com.rifafauzi.footballmatch.ui.searchteam


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.adapter.TeamAdapter
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.databinding.FragmentSearchTeamBinding
import com.rifafauzi.footballmatch.model.teams.Team

/**
 * A simple [Fragment] subclass.
 */
class SearchTeamFragment : BaseFragment<FragmentSearchTeamBinding, SearchTeamViewModel>(), TeamAdapter.OnTeamsPressedListener {

    override fun getLayoutResourceId() = R.layout.fragment_search_team
    override fun getViewModelClass() = SearchTeamViewModel::class.java

    private val adapter = TeamAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initRecyclerView()

        vm.searchTeam.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hideSearchTeam()
                        showLoading()
                    }
                    is Result.HasData -> {
                        showSearchTeam()
                        hideLoading()
                        refreshData(it.data)
                    }
                    is Result.NoData -> {
                        hideSearchTeam()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.empty_data))
                    }
                    is Result.Error -> {
                        hideSearchTeam()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.unknown_error))
                    }
                    is Result.NoInternetConnection -> {
                        hideSearchTeam()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.no_connection))
                    }
                }
            }
        })

    }

    override fun onTeamsPressed(team: Team, position: Int) {
        launchTeamDetail(team.idTeam)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_match, menu)
        val searchView = menu.findItem(R.id.action_search)
        searchView.expandActionView()
        val actionView = searchView.actionView as SearchView
        actionView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    snackBar(resources.getString(R.string.match_empty))
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchTeam(newText)
                return false
            }

        })

        // for handle back stack search view
        MenuItemCompat.setOnActionExpandListener(
            searchView,
            object : MenuItemCompat.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    findNavController().navigateUp()
                    return true
                }

            })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun searchTeam(query: String) = vm.searchMatch(query)

    private fun launchTeamDetail(idTeam: String) {
        val action = SearchTeamFragmentDirections.actionLaunchDetailTeamFragment(idTeam)
        findNavController().navigate(action)
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvSearchTeam.layoutManager = layoutManager
        binding.rvSearchTeam.adapter = adapter
    }

    private fun refreshData(team: List<Team>) {
        adapter.submitList(team)
    }

    private fun showLoading() {
        binding.showLoading = true
    }

    private fun hideLoading() {
        binding.showLoading = false
    }

    private fun showSearchTeam() {
        binding.showData = true
    }

    private fun hideSearchTeam() {
        binding.showData = false
    }

}
