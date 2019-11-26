package com.rifafauzi.footballmatch.ui.searchmatch


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
import com.rifafauzi.footballmatch.adapter.MatchAdapter
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.databinding.FragmentSearchMatchBinding
import com.rifafauzi.footballmatch.model.match.Match
import com.rifafauzi.footballmatch.utils.NEXT_MATCH


/**
 * A simple [Fragment] subclass.
 */
class SearchMatchFragment : BaseFragment<FragmentSearchMatchBinding, SearchMatchViewModel>(), MatchAdapter.OnMatchPressedListener {

    override fun getLayoutResourceId() = com.rifafauzi.footballmatch.R.layout.fragment_search_match
    override fun getViewModelClass() = SearchMatchViewModel::class.java

    private val adapter = MatchAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initRecyclerView()

        vm.searchMatch.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hideSearchMatch()
                        showLoading()
                    }
                    is Result.HasData -> {
                        showSearchMatch()
                        hideLoading()
                        refreshData(it.data)
                    }
                    is Result.NoData -> {
                        hideSearchMatch()
                        hideLoading()
                        longSnackBar("Data not Found")
                    }
                    is Result.Error -> {
                        hideSearchMatch()
                        hideLoading()
                        longSnackBar("Unknown Error")
                    }
                    is Result.NoInternetConnection -> {
                        hideSearchMatch()
                        hideLoading()
                        longSnackBar("No Internet Connection")
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.rifafauzi.footballmatch.R.menu.search_match, menu)
        val searchView = menu.findItem(com.rifafauzi.footballmatch.R.id.action_search)
        searchView.expandActionView()
        val actionView = searchView.actionView as SearchView
        actionView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    snackBar("Query don't empty")
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchMatch(newText)
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

    override fun onMatchPressed(match: Match, position: Int) {
        launchMatchDetail(match.idEvent)
    }

    private fun searchMatch(query: String) = vm.searchMatch(query)

    private fun launchMatchDetail(idEvent: String) {
        val action = SearchMatchFragmentDirections.actionSearchMatchFragmentToDetailMatchFragment(idEvent, NEXT_MATCH)
        findNavController().navigate(action)
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvSearchMatch.layoutManager = layoutManager
        binding.rvSearchMatch.adapter = adapter
    }

    private fun refreshData(match : List<Match>) {
        adapter.submitList(match)
    }

    private fun showLoading() {
        binding.showLoading = true
    }

    private fun hideLoading() {
        binding.showLoading = false
    }

    private fun showSearchMatch() {
        binding.showData = true
    }

    private fun hideSearchMatch() {
        binding.showData = false
    }
}
