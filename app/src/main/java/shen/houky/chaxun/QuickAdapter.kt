package shen.houky.chaxun

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import shen.houky.litepal.R


class QuickAdapter(Tikus: MutableList<MutableList<String>>,item_view_id: Int) :
    BaseQuickAdapter<MutableList<String>, BaseViewHolder>(item_view_id, Tikus) {//后面的参数决定显示多少条信息，即getItemCount()
    private var tikus: MutableList<MutableList<String>> = Tikus
    override fun convert(helper: BaseViewHolder, item: MutableList<String>) {//这个item就是参数Tikus里面的每个List
        helper.setText(R.id.question, item[0])
        helper.setText(R.id.option1, item[1])
        helper.setText(R.id.option2, item[2])
        helper.setText(R.id.option3, item[3])
        helper.setText(R.id.option4, item[4])
        helper.setText(R.id.answer, tikus[helper.position][5])//另一种写法
    }

}
