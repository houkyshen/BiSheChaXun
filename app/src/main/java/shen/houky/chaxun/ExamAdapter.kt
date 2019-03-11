package shen.houky.chaxun

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.RadioButton
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import shen.houky.chaxun.table.TiKu
import shen.houky.litepal.R


class ExamAdapter(Tikus: MutableList<TiKu>, item_view_id: Int, keyVisible: Boolean) :
    BaseQuickAdapter<TiKu, BaseViewHolder>(item_view_id, Tikus) {

    //后面的参数决定显示多少条信息，即getItemCount()
    var keyVisible = keyVisible

    @SuppressLint("ResourceAsColor")
    override fun convert(helper: BaseViewHolder, item: TiKu) {//这个item就是参数Tikus里面的每个List
        helper.setText(R.id.question, item.question)
        helper.setText(R.id.option1, item.option1)
        helper.setText(R.id.option2, item.option2)
        helper.setText(R.id.option3, item.option3)
        helper.setText(R.id.option4, item.option4)
        helper.setText(R.id.answer, item.answer)//另一种写法
        helper.setText(R.id.key, "答案：${item.answer}")//另一种写法
        var radioButtons = listOf(
            helper.getView(R.id.option1), helper.getView(R.id.option2), helper.getView<RadioButton>(
                R.id.option3
            ),
            helper.getView(R.id.option4), helper.getView(R.id.answer)
        )
        calculateScore(radioButtons, helper.getView(R.id.rg1), helper.position)
        if (this.keyVisible) {
            helper.setVisible(R.id.key, true)
            when(keepStatus[helper.position-1]){
                1 -> helper.setChecked(R.id.option1,true)
                2 -> helper.setChecked(R.id.option2,true)
                3 -> helper.setChecked(R.id.option3,true)
                4 -> helper.setChecked(R.id.option4,true)
            }
            when(arrayScore[helper.position-1]){
                0 -> helper.setTextColor(R.id.key,Color.RED)
                1 -> helper.setTextColor(R.id.key,Color.BLUE)
            }
        }
    }

    override fun getItemCount(): Int = 102

}
