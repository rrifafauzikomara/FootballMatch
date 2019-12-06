package com.rifafauzi.footballmatch.ui.searchteam


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.databinding.FragmentSearchTeamBinding

/**
 * A simple [Fragment] subclass.
 */
class SearchTeamFragment : BaseFragment<FragmentSearchTeamBinding, SearchTeamViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_search_team

    override fun getViewModelClass() = SearchTeamViewModel::class.java

}
