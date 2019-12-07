package com.rifafauzi.footballmatch.ui.player


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.adapter.PlayersAdapter
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.databinding.FragmentPlayerListBinding
import com.rifafauzi.footballmatch.model.player.Player
import com.rifafauzi.footballmatch.utils.ID_TEAM

/**
 * A simple [Fragment] subclass.
 */

class PlayerListFragment : BaseFragment<FragmentPlayerListBinding, PlayerListViewModel>(), PlayersAdapter.OnPlayersPressedListener {

    override fun getLayoutResourceId() = R.layout.fragment_player_list
    override fun getViewModelClass() = PlayerListViewModel::class.java

    private val adapter = PlayersAdapter(this)
    private var idTeam:String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        idTeam = arguments?.getString(ID_TEAM)

        vm.getTeamDetail(idTeam)
        vm.detailTeam.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {

                    }
                    is Result.HasData -> {
                        vm.getListPlayer(it.data[0].strTeam)
                    }
                    is Result.NoData -> {

                    }
                    is Result.Error -> {

                    }
                    is Result.NoInternetConnection -> {

                    }
                }
            }
        })

        vm.player.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hidePlayers()
                        showLoading()
                    }
                    is Result.HasData -> {
                        showPlayers()
                        hideLoading()
                        refreshData(it.data)
                    }
                    is Result.NoData -> {
                        hidePlayers()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.empty_data))
                    }
                    is Result.Error -> {
                        hidePlayers()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.unknown_error))
                    }
                    is Result.NoInternetConnection -> {
                        hidePlayers()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.no_connection))
                    }
                }
            }
        })
    }

    override fun onPlayersPressed(player: Player, position: Int) {
        launchDetailPlayer(player.idPlayer)
    }

    private fun launchDetailPlayer(idPlayer: String) {

    }

    private fun refreshData(data: List<Player>) {
        adapter.submitList(data)
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvPlayer.layoutManager = layoutManager
        binding.rvPlayer.adapter = adapter
    }

    private fun showLoading() {
        binding.showLoading = true
    }

    private fun hideLoading() {
        binding.showLoading = false
    }

    private fun showPlayers() {
        binding.showData = true
    }

    private fun hidePlayers() {
        binding.showData = false
    }

}
