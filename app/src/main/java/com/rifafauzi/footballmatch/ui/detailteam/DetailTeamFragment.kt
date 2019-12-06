package com.rifafauzi.footballmatch.ui.detailteam


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.databinding.FragmentDetailTeamBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailTeamFragment : BaseFragment<FragmentDetailTeamBinding, DetailTeamViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_detail_team

    override fun getViewModelClass() = DetailTeamViewModel::class.java


}
