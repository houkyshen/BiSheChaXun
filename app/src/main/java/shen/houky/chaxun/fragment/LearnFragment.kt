package shen.houky.chaxun.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_exam.*
import kotlinx.android.synthetic.main.fragment_learn.*
import kotlinx.android.synthetic.main.fragment_query.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.support.v4.toast
import org.litepal.LitePal
import org.litepal.extension.deleteAll
import org.litepal.extension.findAll
import shen.houky.chaxun.MainActivity
import shen.houky.litepal.*
import shen.houky.chaxun.adapter.TabPageAdapter
import shen.houky.chaxun.fragment.learn.learnFragment1
import shen.houky.chaxun.fragment.learn.learnFragment2
import shen.houky.chaxun.fragment.learn.learnFragment3

class LearnFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_learn, null)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        headerTitle.text = "典型工艺学习"
        toast("欢迎来到学习系统！")
        var tab1 = tabLayout.newTab()
        var tab2 = tabLayout.newTab()
        var tab3 = tabLayout.newTab()
        tabLayout.addTab(tab1)
        tabLayout.addTab(tab2)
        tabLayout.addTab(tab3)
        var fragments = ArrayList<Fragment>()
        fragments.add(learnFragment1())
        fragments.add(learnFragment2())
        fragments.add(learnFragment3())
        var a = context as MainActivity
        var tabNames = listOf("共沉淀法","微乳液法","溶胶-凝胶法")
        val pagerAdapter = TabPageAdapter(a.supportFragmentManager, fragments, tabNames)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}