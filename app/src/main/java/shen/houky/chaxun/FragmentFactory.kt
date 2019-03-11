package shen.houky.chaxun

import android.support.v4.app.Fragment
import shen.houky.chaxun.fragment.ExamFragment
import shen.houky.chaxun.fragment.LearnFragment
import shen.houky.chaxun.fragment.QueryFragment
import shen.houky.litepal.R

/**
 * 碎片工厂
 */
class FragmentFactory private constructor() {

    private val learn by lazy {
        LearnFragment()
    }

    private val query by lazy {
        QueryFragment()
    }

    private val exam by lazy {
        ExamFragment()
    }


    companion object {
        val instance = FragmentFactory()
    }

    fun getFragment(tabId: Int): Fragment? {
        when (tabId) {
            R.id.tab_learn -> return learn
            R.id.tab_query -> return query
            R.id.tab_exam -> return exam
        }
        return null
    }

    fun getExamFragment():Fragment?{
        return exam
    }


}