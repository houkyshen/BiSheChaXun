package shen.houky.chaxun.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import shen.houky.chaxun.initRadioButton
import shen.houky.chaxun.table.TiKu
import shen.houky.litepal.R

class QueryAdapter(Tikus: MutableList<TiKu>, ItemViewId:Int) : RecyclerView.Adapter<QueryAdapter.ViewHolder>() {
    private var tikus: MutableList<TiKu> = Tikus
    private var id = ItemViewId
    /**
     * 更新数据
     */
    fun updateTikus(Tikus: MutableList<TiKu>) {
//        if(list==null) return

        Tikus?.let {
            tikus.clear()
            tikus.addAll(Tikus)
            notifyDataSetChanged()
        }
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var question = itemView.findViewById<TextView>(R.id.question)
        var option1 = itemView.findViewById<RadioButton>(R.id.option1)
        var option2 = itemView.findViewById<RadioButton>(R.id.option2)
        var option3 = itemView.findViewById<RadioButton>(R.id.option3)
        var option4 = itemView.findViewById<RadioButton>(R.id.option4)
        var answer = itemView.findViewById<RadioButton>(R.id.answer)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        //var view = View.inflate(p0.context, R.layout.item_view, p0)//应该读取一个布局，而不是一个view，所以这个用法错误
        var view = LayoutInflater.from(p0.context).inflate(id, p0, false)
        var holder = ViewHolder(view)
        holder.option1.visibility = View.GONE;holder.option2.visibility = View.GONE;holder.option3.visibility = View.GONE;holder.option4.visibility = View.GONE
        holder.answer.visibility = View.GONE

        holder.question.setOnClickListener {
            holder.setIsRecyclable(false)
            if (holder.option1.visibility == View.GONE){
                holder.option1.visibility = View.VISIBLE;holder.option2.visibility = View.VISIBLE;holder.option3.visibility = View.VISIBLE;holder.option4.visibility = View.VISIBLE
                holder.setIsRecyclable(true)
            }else{
                holder.option1.visibility = View.GONE;holder.option2.visibility = View.GONE;holder.option3.visibility = View.GONE;holder.option4.visibility = View.GONE
            }

//            holder.setIsRecyclable(false)
        }
//        holder.setIsRecyclable(false)
        return holder
    }

    override fun getItemCount(): Int = tikus.size

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        var tiku = tikus[p1]
        holder.question.text = tiku.question
        holder.option1.text = tiku.option1
        holder.option2.text = tiku.option2
        holder.option3.text = tiku.option3
        holder.option4.text = tiku.option4
        holder.answer.text = tiku.answer
        var radioButtons = listOf(holder.option1,holder.option2,holder.option3,holder.option4,holder.answer)
        radioButtons.initRadioButton()//调用扩展函数
    }
}