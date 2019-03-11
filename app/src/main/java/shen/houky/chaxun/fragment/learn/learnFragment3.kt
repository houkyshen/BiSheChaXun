package shen.houky.chaxun.fragment.learn

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_exam.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.support.v4.toast
import org.litepal.LitePal
import org.litepal.extension.deleteAll
import org.litepal.extension.findAll
import shen.houky.litepal.*
import shen.houky.chaxun.headerAndfooter.HeaderOrFooterViewExam
import shen.houky.chaxun.table.TiKu

class learnFragment3 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_learn3, null)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}