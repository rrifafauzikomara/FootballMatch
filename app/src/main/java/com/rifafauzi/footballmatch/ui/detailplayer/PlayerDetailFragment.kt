package com.rifafauzi.footballmatch.ui.detailplayer


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.databinding.FragmentPlayerDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class PlayerDetailFragment : BaseFragment<FragmentPlayerDetailBinding, PlayerDetailViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_player_detail

    override fun getViewModelClass() = PlayerDetailViewModel::class.java

}
