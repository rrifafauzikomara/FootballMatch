package com.rifafauzi.footballmatch.ui.detailleague


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.databinding.FragmentDetailLeagueBinding
import com.rifafauzi.footballmatch.model.leagues.Leagues

/**
 * A simple [Fragment] subclass.
 */
class DetailLeagueFragment : BaseFragment<FragmentDetailLeagueBinding, DetailLeagueViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_detail_league

    override fun getViewModelClass() = DetailLeagueViewModel::class.java

    private var idLeague: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val safeArgs = DetailLeagueFragmentArgs.fromBundle(it)
            idLeague = safeArgs.idLeague
        }

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

    fun onPressedPrevMatch() {
        launchPreviousMatch()
    }

    fun onPressedNextMatch() {
        launchNextMatch()
    }

    private fun launchNextMatch() {
        val action = DetailLeagueFragmentDirections.actionLaunchNextMatchFragment(idLeague)
        findNavController().navigate(action)
    }

    private fun launchPreviousMatch() {
        val action = DetailLeagueFragmentDirections.actionLaunchPreviousFragment(idLeague)
        findNavController().navigate(action)
    }

    private fun displayData(data: List<Leagues>) {
        binding.data = data[0]
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