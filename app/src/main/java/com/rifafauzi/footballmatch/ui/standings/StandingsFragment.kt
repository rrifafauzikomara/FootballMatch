package com.rifafauzi.footballmatch.ui.standings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.adapter.StandingAdapter
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.databinding.FragmentStandingsBinding
import com.rifafauzi.footballmatch.model.standing.Standings
import com.rifafauzi.footballmatch.utils.ID_LEAGUES

/**
 * A simple [Fragment] subclass.
 */
class StandingsFragment : BaseFragment<FragmentStandingsBinding, StandingsViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_standings
    override fun getViewModelClass() = StandingsViewModel::class.java

    private val adapter = StandingAdapter()
    private var idLeague:String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        idLeague = arguments?.getString(ID_LEAGUES)

        vm.getListStanding(idLeague)
        vm.standing.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hideNextMatch()
                        hideLayoutEmpty()
                        showLoading()
                    }
                    is Result.HasData -> {
                        showNextMatch()
                        hideLoading()
                        hideLayoutEmpty()
                        refreshData(it.data)
                    }
                    is Result.NoData -> {
                        hideNextMatch()
                        hideLoading()
                        showLayoutEmpty()
                    }
                    is Result.Error -> {
                        hideNextMatch()
                        hideLoading()
                        hideLayoutEmpty()
                        longSnackBar(resources.getString(R.string.unknown_error))
                    }
                    is Result.NoInternetConnection -> {
                        hideNextMatch()
                        hideLoading()
                        hideLayoutEmpty()
                        longSnackBar(resources.getString(R.string.no_connection))
                    }
                }
            }
        })

    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvStanding.layoutManager = layoutManager
        binding.rvStanding.adapter = adapter
    }

    private fun refreshData(standing : List<Standings>) {
        adapter.submitList(standing)
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

    private fun showLayoutEmpty() {
        binding.layoutEmptyData.visibility = View.VISIBLE
    }

    private fun hideLayoutEmpty() {
        binding.layoutEmptyData.visibility = View.GONE
    }

}
