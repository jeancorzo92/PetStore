package com.app.petstore.views.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.app.petstore.views.CustomInfoFragment
import com.app.petstore.views.PetDetailFragment

class PetDetailPageAdapter(fragmentManager: FragmentManager,
                           val tabCount: Int) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> PetDetailFragment()
            1 -> CustomInfoFragment()
            else -> throw Exception("Didn't set any fragment for page $position")
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

}