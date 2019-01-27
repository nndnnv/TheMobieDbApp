package com.tikal.app.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.tikal.app.utils.App
import com.tikal.app.R
import com.tikal.app.ui.fragment.MoviesFragment
import com.tikal.app.ui.fragment.TvShowsFragment

class MainFragmentsAdapter(fm: FragmentManager, private val mContext: Context) : FragmentPagerAdapter(fm)  {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MoviesFragment.newInstance()
            }
            1 -> TvShowsFragment.newInstance()
            else -> {
                return MoviesFragment.newInstance()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> mContext.getString(R.string.top_movies_title)
            1 -> mContext.getString(R.string.top_tv_shows_title)
            else -> {
                return mContext.getString(R.string.top_movies_title)
            }
        }
    }
}