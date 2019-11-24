package com.rifafauzi.footballmatch.ui.searchmatch


import androidx.fragment.app.Fragment
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.databinding.FragmentSearchMatchBinding

/**
 * A simple [Fragment] subclass.
 */
class SearchMatchFragment : BaseFragment<FragmentSearchMatchBinding, SearchMatchViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_search_match

    override fun getViewModelClass() = SearchMatchViewModel::class.java
}
