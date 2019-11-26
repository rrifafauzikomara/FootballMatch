package com.rifafauzi.footballmatch.ui.favorite


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
        viewPagerAdapter = ViewPagerFragment(childFragmentManager, requireContext())
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager,true)
    }

    class ViewPagerFragment(fm: FragmentManager, context: Context): FragmentPagerAdapter(fm) {

        private val tabTitles = arrayOf(context.resources.getString(R.string.previous_match), context.resources.getString(R.string.next_match))

        private val pages = listOf(
            FavoritePrevMatchFragment(),
            FavoriteNextMatchFragment()
        )
        override fun getItem(position: Int): Fragment {
            return pages[position]
        }

        override fun getCount(): Int {
            return pages.size
        }
        override fun getPageTitle(position: Int): CharSequence? {
            return tabTitles[position]
        }
    }

}
