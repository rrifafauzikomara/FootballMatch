package com.rifafauzi.footballmatch.ui.standings


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.databinding.FragmentStandingsBinding

/**
 * A simple [Fragment] subclass.
 */
class StandingsFragment : BaseFragment<FragmentStandingsBinding, StandingsViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_standings

    override fun getViewModelClass() = StandingsViewModel::class.java

}
