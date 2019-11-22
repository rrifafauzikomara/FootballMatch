package com.rifafauzi.footballmatch.ui.leagues


import androidx.fragment.app.Fragment
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.databinding.FragmentLeaguesBinding

/**
 * A simple [Fragment] subclass.
 */
class LeaguesFragment : BaseFragment<FragmentLeaguesBinding, LeaguesViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_leagues

    override fun getViewModelClass() = LeaguesViewModel::class.java



}
