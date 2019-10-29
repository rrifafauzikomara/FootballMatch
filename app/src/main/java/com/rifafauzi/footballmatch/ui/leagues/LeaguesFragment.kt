package com.rifafauzi.footballmatch.ui.leagues

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.data.local.entity.Leagues
import com.rifafauzi.footballmatch.databinding.FragmentLeaguesBinding
import com.rifafauzi.footballmatch.vo.Result

/**
 * A simple [Fragment] subclass.
 */
class LeaguesFragment : BaseFragment<FragmentLeaguesBinding, LeaguesViewModel>(), LeaguesAdapter.OnLeaguesPressedListener {

    private val adapter = LeaguesAdapter (listOf(), this)

    override fun getLayoutResourceId(): Int = R.layout.fragment_leagues

    override fun getViewModelClass(): Class<LeaguesViewModel> = LeaguesViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        binding.rvLeagues.layoutManager = layoutManager
        binding.rvLeagues.adapter = adapter

        vm.listLeagues.observe(this, Observer { data -> getAllLeagues(data)})

    }

    private fun getAllLeagues(data: Result<List<Leagues>>?) {
        data?.let {
            when (it.status) {
                Result.Status.LOADING -> {
                    binding.stateView.showLoading()
                }
                Result.Status.ERROR -> {
                    binding.stateView.hideLoading()
                }
                Result.Status.SUCCESS -> {
                    binding.stateView.hideLoading()
                    refreshData(it.data ?: emptyList())
                }
            }
        }
    }

    private fun refreshData(data: List<Leagues>) {
        adapter.refreshData(data)
    }

    override fun onLeaguesPressed(banner: Leagues, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
