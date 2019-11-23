package com.rifafauzi.footballmatch.ui.detailleague


import androidx.fragment.app.Fragment
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.databinding.FragmentDetailLeagueBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailLeagueFragment : BaseFragment<FragmentDetailLeagueBinding, DetailLeagueViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_detail_league

    override fun getViewModelClass() = DetailLeagueViewModel::class.java

}