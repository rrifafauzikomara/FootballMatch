package com.rifafauzi.footballmatch.ui.detailmatch


import androidx.fragment.app.Fragment
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.databinding.FragmentDetailMatchBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailMatchFragment : BaseFragment<FragmentDetailMatchBinding, DetailMatchViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_detail_match

    override fun getViewModelClass() = DetailMatchViewModel::class.java
}