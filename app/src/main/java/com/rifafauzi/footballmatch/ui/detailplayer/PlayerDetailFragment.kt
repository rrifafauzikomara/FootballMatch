package com.rifafauzi.footballmatch.ui.detailplayer


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.databinding.FragmentPlayerDetailBinding
import com.rifafauzi.footballmatch.model.player.Player

/**
 * A simple [Fragment] subclass.
 */
class PlayerDetailFragment : BaseFragment<FragmentPlayerDetailBinding, PlayerDetailViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_player_detail
    override fun getViewModelClass() = PlayerDetailViewModel::class.java

    private var idPlayer: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val safeArgs = PlayerDetailFragmentArgs.fromBundle(it)
            idPlayer = safeArgs.idPlayer
        }

        vm.getDetailPlayer(idPlayer)
        vm.player.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hidePlayer()
                        showLoading()
                    }
                    is Result.HasData -> {
                        showPlayer()
                        hideLoading()
                        displayData(it.data)
                    }
                    is Result.NoData -> {
                        hidePlayer()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.empty_data))
                    }
                    is Result.Error -> {
                        hidePlayer()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.unknown_error))
                    }
                    is Result.NoInternetConnection -> {
                        hidePlayer()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.no_connection))
                    }
                }
            }
        })
    }

    private fun displayData(data: List<Player>) {
        binding.data = data[0]
    }

    private fun showLoading() {
        binding.showLoading = true
    }

    private fun hideLoading() {
        binding.showLoading = false
    }

    private fun showPlayer() {
        binding.showData = true
    }

    private fun hidePlayer() {
        binding.showData = false
    }

}
