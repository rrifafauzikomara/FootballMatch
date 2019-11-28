package com.rifafauzi.footballmatch.ui.previousmatch


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
import com.rifafauzi.footballmatch.databinding.FragmentPreviousMatchBinding
import com.rifafauzi.footballmatch.model.match.Match
import com.rifafauzi.footballmatch.utils.PREV_MATCH

/**
 * A simple [Fragment] subclass.
 */
class PreviousMatchFragment : BaseFragment<FragmentPreviousMatchBinding, PreviousMatchViewModel>(), MatchAdapter.OnMatchPressedListener {

    override fun getLayoutResourceId() = R.layout.fragment_previous_match
    override fun getViewModelClass() = PreviousMatchViewModel::class.java

    private val adapter = MatchAdapter(this)
    private var idLeague: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        arguments?.let {
            val safeArgs = PreviousMatchFragmentArgs.fromBundle(it)
            idLeague = safeArgs.idLeague
        }

        vm.getPrevMatch(idLeague)
        vm.prevMatch.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hidePrevMatch()
                        showLoading()
                    }
                    is Result.HasData -> {
                        showPrevMatch()
                        hideLoading()
                        refreshData(it.data)
                    }
                    is Result.NoData -> {
                        hidePrevMatch()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.empty_data))
                    }
                    is Result.Error -> {
                        hidePrevMatch()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.unknown_error))
                    }
                    is Result.NoInternetConnection -> {
                        hidePrevMatch()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.no_connection))
                    }
                }
            }
        })
    }

    override fun onMatchPressed(match: Match, position: Int) {
        launchDetailMatch(match.idEvent)
    }

    private fun launchDetailMatch(idEvent: String) {
        val action = PreviousMatchFragmentDirections.actionPreviousFragmentToDetailMatchFragment(idEvent, PREV_MATCH)
        findNavController().navigate(action)
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvPrevMatch.layoutManager = layoutManager
        binding.rvPrevMatch.adapter = adapter
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

    private fun showPrevMatch() {
        binding.showData = true
    }

    private fun hidePrevMatch() {
        binding.showData = false
    }

}
