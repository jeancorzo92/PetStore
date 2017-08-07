package com.app.petstore.views

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import com.app.petstore.R
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import com.app.petstore.views.adapters.PetDetailPageAdapter
import org.jetbrains.anko.find


class PetDetailActivity : AppCompatActivity() {

    val tabLayout by lazy { find<TabLayout>(R.id.tab_layout) }
    val viewPager by lazy { find<ViewPager>(R.id.pager) }
    val pageAdapter by lazy { PetDetailPageAdapter(supportFragmentManager, tabLayout.tabCount) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_detail)

        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.pet_detail_tab)))
        tabLayout.addTab(tabLayout.newTab().setText(resources.getString(R.string.custom_tab)))

        viewPager.adapter = pageAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
        })
    }
}
