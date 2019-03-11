package shen.houky.chaxun.headerAndfooter

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.footer_view_exam.view.*
import shen.houky.litepal.R
import shen.houky.chaxun.totalScore

class HeaderOrFooterViewExam(context: Context?, viewID: Int) : LinearLayout(context) {
    init {
        View.inflate(context, viewID, this)//root指的是父布局
        if (viewID == R.layout.footer_view_exam)
            btn_commitAnswer.setOnClickListener {
                context?.totalScore()//调用Context的扩展函数
            }
    }
}