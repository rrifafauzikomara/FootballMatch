package com.rifafauzi.footballmatch.ui.teamoverview


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.databinding.FragmentTeamOverviewBinding

/**
 * A simple [Fragment] subclass.
 */
class TeamOverviewFragment : BaseFragment<FragmentTeamOverviewBinding, TeamOverviewViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_team_overview

    override fun getViewModelClass() = TeamOverviewViewModel::class.java

}
