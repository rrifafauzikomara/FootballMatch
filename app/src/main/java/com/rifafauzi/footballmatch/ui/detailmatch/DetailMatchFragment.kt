package com.rifafauzi.footballmatch.ui.detailmatch


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.databinding.FragmentDetailMatchBinding
import com.rifafauzi.footballmatch.model.leagues.Leagues
import com.rifafauzi.footballmatch.model.match.Match

/**
 * A simple [Fragment] subclass.
 */
class DetailMatchFragment : BaseFragment<FragmentDetailMatchBinding, DetailMatchViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_detail_match

    override fun getViewModelClass() = DetailMatchViewModel::class.java

    private lateinit var idEvent: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        idEvent = DetailMatchFragmentArgs.fromBundle(arguments!!).idEvent

        vm.getDetailMatch(idEvent)
        vm.detailMatch.observe(viewLifecycleOwner, Observer {
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

    private fun displayData(data: List<Match>) {
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