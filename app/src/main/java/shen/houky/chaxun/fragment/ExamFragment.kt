package shen.houky.chaxun.fragment

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
import shen.houky.chaxun.ExamAdapter
import shen.houky.litepal.*
import shen.houky.chaxun.headerAndfooter.HeaderOrFooterViewExam
import shen.houky.chaxun.randomString
import shen.houky.chaxun.randomTiMu
import shen.houky.chaxun.readFile
import shen.houky.chaxun.table.TiKu

class ExamFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exam, null)
    }
    lateinit var adapter1: ExamAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        LitePal.getDatabase()//创建数据库，并且litepal会帮我们在数据库内自动创建表格
        context?.let {
            var question = readFile(R.raw.question, it)
            var option1 = readFile(R.raw.option1, it)
            var option2 = readFile(R.raw.option2, it)
            var option3 = readFile(R.raw.option3, it)
            var option4 = readFile(R.raw.option4, it)
            var answer = readFile(R.raw.answer, it)
            var randomStrings = randomString(question)
            question = randomTiMu(question, randomStrings)
            for (i in 0 until question.size) {
                question[i] = "${i + 1}. ${question[i]}"
            }
            option1 = randomTiMu(option1, randomStrings)
            option2 = randomTiMu(option2, randomStrings)
            option3 = randomTiMu(option3, randomStrings)
            option4 = randomTiMu(option4, randomStrings)
            answer = randomTiMu(answer, randomStrings)
            LitePal.deleteAll<TiKu>()//删除某个表格的所有数据
            for (i in 0..99) {
                var tiKu = TiKu()//创建一行新数据，而不是一个新的表
                tiKu.question = question[i]
                tiKu.option1 = option1[i]
                tiKu.option2 = option2[i]
                tiKu.option3 = option3[i]
                tiKu.option4 = option4[i]
                tiKu.answer = answer[i]
                tiKu.save()
            }
        }

        var tikus = LitePal.findAll<TiKu>()
        headerTitle.text = "知识考试系统"
        toast("欢迎来到知识考试系统！")
        adapter1 = ExamAdapter(tikus, R.layout.item_view_exam, false)
        rv_exam?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adapter1
        }
        adapter1.addHeaderView(
            HeaderOrFooterViewExam(
                context,
                R.layout.header_view_exam
            )
        )
        adapter1.addFooterView(
            HeaderOrFooterViewExam(
                context,
                R.layout.footer_view_exam
            )
        )
        btn_startExam.setOnClickListener {
            btn_startExam.visibility = View.GONE
            linearLayout1.visibility = View.GONE
            rv_exam.scrollToPosition(0)
        }
        rv_exam.setItemViewCacheSize(300)//解决复用问题，只要把缓存设置得比要显示的项目要多，就可以了
//        adapter1.mode = 1
        adapter1.setOnItemClickListener { adapter, view, position ->
            toast("$position")
        }

    }

    fun checkKey(){
        adapter1.keyVisible = true
        adapter1.notifyDataSetChanged()
        rv_exam.scrollToPosition(0)
    }
}