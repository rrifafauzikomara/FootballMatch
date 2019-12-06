package com.rifafauzi.footballmatch.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by rrifafauzikomara on 2019-12-05.
 */

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