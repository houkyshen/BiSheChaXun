package shen.houky.chaxun.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_query.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.support.v4.toast
import org.litepal.LitePal
import org.litepal.extension.deleteAll
import org.litepal.extension.find
import org.litepal.extension.findAll
import shen.houky.chaxun.adapter.QueryAdapter
import shen.houky.chaxun.randomString
import shen.houky.chaxun.randomTiMu
import shen.houky.chaxun.readFile
import shen.houky.litepal.*
import shen.houky.chaxun.table.TiKu

class QueryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_query, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        headerTitle.text = "知识查询系统"
        toast("欢迎来到知识查询系统！")
        LitePal.getDatabase()//创建数据库，并且litePal会帮我们在数据库内自动创建表格
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
            for (i in question.indices) {
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
        var adapter1 = QueryAdapter(tikus, R.layout.item_view_query)
        rv_query?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adapter1
        }
        class MyTextWatcher : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var searchResult = LitePal.select()
                    .where("question like ?", "%${editText_search.text.trim()}%").find<TiKu>() as MutableList<TiKu>
//                Log.i("5201314", " 查询结果：${searchResult[0].question}")
                adapter1.updateTikus(searchResult)
//                adapter1.notifyDataSetChanged()
            }
        }
        editText_search.addTextChangedListener(MyTextWatcher())//添加编辑框监听器
        rv_query.setItemViewCacheSize(500)
    }
}