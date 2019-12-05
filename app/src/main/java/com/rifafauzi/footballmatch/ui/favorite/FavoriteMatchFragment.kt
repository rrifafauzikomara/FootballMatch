package com.rifafauzi.footballmatch.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.databinding.FragmentFavoriteMatchBinding

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMatchFragment : Fragment() {

    private lateinit var viewPager : ViewPager
    private lateinit var viewPagerAdapter : ViewPagerFragment
    private lateinit var tabLayout : TabLayout
    private lateinit var binding: FragmentFavoriteMatchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_match, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = binding.viewPagerFavorite
        tabLayout = binding.tabLayoutFavorite

        val tabPrevMatch = requireContext().resources.getString(R.string.previous_match)
        val tabNextMatch = requireContext().resources.getString(R.string.next_match)
        val tabTeam = requireContext().resources.getString(R.string.team)

        viewPagerAdapter = ViewPagerFragment(childFragmentManager)
        viewPagerAdapter.addFragment(FavoritePrevMatchFragment(), tabPrevMatch)
        viewPagerAdapter.addFragment(FavoriteNextMatchFragment(), tabNextMatch)
        viewPagerAdapter.addFragment(FavoriteTeamFragment(), tabTeam)

        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager,true)
    }

    class ViewPagerFragment(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        private val mFragmentList: ArrayList<Fragment> = ArrayList()
        private val mFragmentTitleList: ArrayList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }

}
