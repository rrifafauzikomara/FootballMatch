package com.rifafauzi.footballmatch.ui.player


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.databinding.FragmentPlayerListBinding

/**
 * A simple [Fragment] subclass.
 */
class PlayerListFragment : BaseFragment<FragmentPlayerListBinding, PlayerListViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_player_list

    override fun getViewModelClass() = PlayerListViewModel::class.java

}
