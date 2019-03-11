package shen.houky.chaxun.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TabPageAdapter(fm: FragmentManager, private val fragments: List<Fragment>, tabNames: List<String>) :
    FragmentPagerAdapter(fm) {
    private val tabNames = tabNames

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabNames[position]
    }
    
}