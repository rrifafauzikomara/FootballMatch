package com.rifafauzi.footballmatch.ui.detailmatch


import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.databinding.FragmentDetailMatchBinding
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

        //Enable cross fade transition on glide
        val transition = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

        idEvent = DetailMatchFragmentArgs.fromBundle(arguments!!).idEvent

        vm.getDetailMatch(idEvent)
        vm.detailMatch.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hideMatch()
                        showLoading()
                    }
                    is Result.HasData -> {
                        showMatch()
                        hideLoading()
                        displayData(it.data)
                    }
                    is Result.NoData -> {
                        hideMatch()
                        hideLoading()
                        longSnackBar("Data not Found")
                    }
                    is Result.Error -> {
                        hideMatch()
                        hideLoading()
                        longSnackBar("Unknown Error")
                    }
                    is Result.NoInternetConnection -> {
                        hideMatch()
                        hideLoading()
                        longSnackBar("No Internet Connection")
                    }
                }
            }
        })

        vm.teamHome.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hideImage()
                        showProgressImage()
                    }
                    is Result.HasData -> {
                        showImage()
                        hideProgressImage()
                        displayTeamLogo(it.data[0].strTeamBadge, binding.homeImg, transition)
                    }
                    is Result.NoData -> {
                        hideImage()
                        hideProgressImage()
                    }
                    is Result.Error -> {
                        hideImage()
                        hideProgressImage()
                    }
                    is Result.NoInternetConnection -> {
                        hideImage()
                        hideProgressImage()
                    }
                }
            }
        })

        vm.awayTeam.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hideImage()
                        showProgressImage()
                    }
                    is Result.HasData -> {
                        showImage()
                        hideProgressImage()
                        displayTeamLogo(it.data[0].strTeamBadge, binding.awayImg, transition)
                    }
                    is Result.NoData -> {
                        hideImage()
                        hideProgressImage()
                    }
                    is Result.Error -> {
                        hideImage()
                        hideProgressImage()
                    }
                    is Result.NoInternetConnection -> {
                        hideImage()
                        hideProgressImage()
                    }
                }
            }
        })
    }

    private fun displayData(data: List<Match>) {
        binding.data = data[0]
        vm.getDetailTeamHome(data[0].idHomeTeam!!)
        vm.getDetailTeamAway(data[0].idAwayTeam!!)
    }

    private fun displayTeamLogo(teamUrl: String?, teamImage: ImageView, transition: DrawableCrossFadeFactory) {
        Glide.with(requireContext()).load(teamUrl)
            .transition(DrawableTransitionOptions.withCrossFade(transition))
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_trophy)
                    .error(R.drawable.ic_error))
            .into(teamImage)
    }

    private fun showLoading() {
        binding.showLoading = true
    }

    private fun hideLoading() {
        binding.showLoading = false
    }

    private fun showMatch() {
        binding.showData = true
    }

    private fun hideMatch() {
        binding.showData = false
    }

    private fun showProgressImage() {
        binding.showProgress = true
    }

    private fun hideProgressImage() {
        binding.showProgress = false
    }

    private fun showImage() {
        binding.showImage = true
    }

    private fun hideImage() {
        binding.showImage = false
    }
}