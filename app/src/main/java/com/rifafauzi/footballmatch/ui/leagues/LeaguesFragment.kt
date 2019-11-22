package com.rifafauzi.footballmatch.ui.leagues


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.databinding.FragmentLeaguesBinding
import com.rifafauzi.footballmatch.model.Leagues

/**
 * A simple [Fragment] subclass.
 */
class LeaguesFragment : BaseFragment<FragmentLeaguesBinding, LeaguesViewModel>(), LeaguesAdapter.OnLeaguesPressedListener {

    override fun getLayoutResourceId() = R.layout.fragment_leagues
    override fun getViewModelClass() = LeaguesViewModel::class.java

    private val adapter = LeaguesAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        vm.getListLeagues()
        vm.leagues.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hideLeagues()
                        showLoading()
                    }
                    is Result.HasData -> {
                        showLeagues()
                        hideLoading()
                        refreshData(it.data)
                    }
                    is Result.NoData -> {
                        hideLeagues()
                        hideLoading()
                        longSnackBar("Data not Found")
                    }
                    is Result.Error -> {
                        hideLeagues()
                        hideLoading()
                        longSnackBar("Unknown Error")
                    }
                    is Result.NoInternetConnection -> {
                        hideLeagues()
                        hideLoading()
                        longSnackBar("No Internet Connection")
                    }
                }
            }
        })

    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvLeagues.layoutManager = layoutManager
        binding.rvLeagues.adapter = adapter
    }

    private fun refreshData(leagues : List<Leagues>) {
        adapter.submitList(leagues)
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

    override fun onLeaguesPressed(leagues: Leagues, position: Int) {
    }

}
