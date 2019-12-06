package com.rifafauzi.footballmatch.ui.teams


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.databinding.FragmentTeamBinding

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : BaseFragment<FragmentTeamBinding, TeamViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_team

    override fun getViewModelClass() = TeamViewModel::class.java

}
