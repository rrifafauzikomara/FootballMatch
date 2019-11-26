package com.rifafauzi.footballmatch.ui.nextmatch


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.adapter.MatchAdapter
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.databinding.FragmentNextMatchBinding
import com.rifafauzi.footballmatch.model.match.Match
import com.rifafauzi.footballmatch.utils.NEXT_MATCH

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : BaseFragment<FragmentNextMatchBinding, NextMatchViewModel>(), MatchAdapter.OnMatchPressedListener {

    override fun getLayoutResourceId() = R.layout.fragment_next_match
    override fun getViewModelClass() = NextMatchViewModel::class.java

    private val adapter = MatchAdapter(this)
    private var idLeague:String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        arguments?.let {
            val safeArgs = NextMatchFragmentArgs.fromBundle(it)
            idLeague = safeArgs.idLeague
        }

        vm.getNextMatch(idLeague)
        vm.nextMatch.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hideNextMatch()
                        showLoading()
                    }
                    is Result.HasData -> {
                        showNextMatch()
                        hideLoading()
                        refreshData(it.data)
                    }
                    is Result.NoData -> {
                        hideNextMatch()
                        hideLoading()
                        longSnackBar("Data not Found")
                    }
                    is Result.Error -> {
                        hideNextMatch()
                        hideLoading()
                        longSnackBar("Unknown Error")
                    }
                    is Result.NoInternetConnection -> {
                        hideNextMatch()
                        hideLoading()
                        longSnackBar("No Internet Connection")
                    }
                }
            }
        })

    }

    override fun onMatchPressed(match: Match, position: Int) {
        launchDetailMatch(match.idEvent)
    }

    private fun launchDetailMatch(idEvent: String) {
        val action = NextMatchFragmentDirections.actionNextMatchFragmentToDetailMatchFragment(idEvent, NEXT_MATCH)
        findNavController().navigate(action)
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvNextMatch.layoutManager = layoutManager
        binding.rvNextMatch.adapter = adapter
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

    private fun showNextMatch() {
        binding.showData = true
    }

    private fun hideNextMatch() {
        binding.showData = false
    }
}
