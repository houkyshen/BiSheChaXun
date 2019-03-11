package shen.houky.chaxun

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import org.jetbrains.anko.*
import shen.houky.chaxun.fragment.ExamFragment
import shen.houky.chaxun.table.TiKu
import shen.houky.litepal.R
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

var score = 0
var arrayScore: IntArray = IntArray(100)
var a = R.id.option1
var b = R.id.option2
var c = R.id.option3
var d = R.id.option4
var keepStatus = IntArray(100)
fun readFile(fileId: Int, context: Context): MutableList<String> {
    var resourceAsStream: InputStream? = null
    var line: MutableList<String> = mutableListOf()
    if (context is AppCompatActivity)
        resourceAsStream = context.resources.openRawResource(fileId)
    if (resourceAsStream != null) {
        val bufferedReader = BufferedReader(InputStreamReader(resourceAsStream, "GBK"))
        var line = bufferedReader.readLines() as MutableList
        return line
    } else println("找不到文件")
    return line
}

fun randomString(stringList: List<String>): List<String> {
    var str = mutableListOf<String>()
    for (i in 0 until stringList.size)
        str.add("$i")
    var randomStrings = mutableListOf<String>()
    while (randomStrings.size < str.size) {
        var randomString: String = str.random()
        if (!randomStrings.contains(randomString)) {
            randomStrings.add(randomString)
        }
    }
    return randomStrings//random函数返回一个随机字符串数列，字符串里面都是数字，长度取决于传入的字符串数组的长度
}

fun randomTiMu(stringList: List<String>, randomStrings: List<String>): MutableList<String> {
    var randomTiMu = mutableListOf<String>()
    for (i in 0 until stringList.size) {
        randomTiMu.add(stringList[randomStrings[i].toInt()])
    }
    return randomTiMu
}

fun List<RadioButton>.initRadioButton() {
    var radioButtons = this
            when (radioButtons[4].text.toString().trim()) {
        "a" -> radioButtons[0].isChecked = true
        "b" -> radioButtons[1].isChecked = true
        "c" -> radioButtons[2].isChecked = true
        "d" -> radioButtons[3].isChecked = true
    }
}

/**
 * 为Context写扩展函数totalScore()
 */
fun Context.totalScore() {
    for (int in arrayScore) {
        score += int
    }
    fun AlertBuilder<*>.yesButton1(handler: (dialog: DialogInterface) -> Unit) =
        positiveButton("查看答案", handler)
    var context = this
    this?.alert("你的分数是：$score", "考试成绩") {
        yesButton1 {
            if (context is MainActivity)  {
                var a = FragmentFactory.instance.getExamFragment()
                if (a is ExamFragment)  a.checkKey()
            }
        }
    }?.show()
}

fun calculateScore(radioButtons: List<RadioButton>, radioGroup: RadioGroup, position: Int) {
    radioGroup.setOnCheckedChangeListener { group, checkedId ->
        if (judge(checkedId, radioButtons[4].text.toString().trim())) arrayScore[position-1] = 1
        else arrayScore[position-1] = 0
        when(checkedId){
            a -> keepStatus[position-1] = 1
            b -> keepStatus[position-1] = 2
            c -> keepStatus[position-1] = 3
            d -> keepStatus[position-1] = 4
        }
    }
}

fun judge(checkId: Int, answer: String): Boolean {
    var answerInt = 0
    when (answer) {
        "a" -> answerInt = a
        "b" -> answerInt = b
        "c" -> answerInt = c
        "d" -> answerInt = d
    }
    return checkId == answerInt
}

fun log(msg:String,tag:String = "`"){
    Log.i(tag,msg)
}
